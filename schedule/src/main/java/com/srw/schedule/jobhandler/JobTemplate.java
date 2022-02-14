package com.srw.schedule.jobhandler;

import cn.hutool.json.JSONUtil;
import com.srw.common.bean.JsonResult;
import com.xxl.job.core.context.XxlJobHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.utils.$;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Description: 定时任务模板
 * @Author: renwei.song
 * @Date: 2021/3/31 16:30
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JobTemplate<T> {

    private int nThread = Runtime.getRuntime().availableProcessors();

    /**
     *  任务执行
     *  @param queryData 查询数据
     * @param executeData 执行数据
     */
    void execute(Supplier<JsonResult<List<T>>> queryData, Function<T, JsonResult<?>> executeData) {
        XxlJobHelper.log("{}计划任务执行开始", "UserJob");
        long start = System.currentTimeMillis();
        LongAdder skipCount = new LongAdder();
        try {
            JsonResult<List<T>> jsonResult = queryData.get();
            assert jsonResult != null;
            Assert.isTrue(JsonResult.isSuccess(jsonResult), MessageFormat.format("{0}查询失败:{1}", "UserJob", JSONUtil.toJsonStr(jsonResult)));
            List<T> list = jsonResult.getData();
            if (!CollectionUtils.isEmpty(list)) {
                // 异步执行
                ExecutorService executor = Executors.newFixedThreadPool(Math.min(list.size(), nThread * 2));
                CompletableFuture<?>[] futures = list.stream().map(data -> CompletableFuture.runAsync(() -> {
                    try {
                        XxlJobHelper.log("任务id:{},开始执行...", data);
                        JsonResult<?> result = executeData.apply(data);
                        Assert.isTrue(JsonResult.isSuccess(result), $.toJson(result));
                        XxlJobHelper.log("任务id:{},执行结束!!!", data);
                    } catch (Exception ex) {
                        skipCount.increment();
                        XxlJobHelper.log("执行{}报错,param:{},Exception:{}", "UserJob", $.toJson(data), ex);
                    }
                }, executor)).toArray(CompletableFuture[]::new);
                // 等待所有线程执行完毕
                CompletableFuture.allOf(futures).join();
                XxlJobHelper.log("计划任务处理总数量:{},失败数量:{}", list.size(), skipCount.intValue());

                shutdown(executor);
            }
        } catch (RuntimeException e) {
            log.info("计划任务执行异常！", e);
        }

        XxlJobHelper.log("{}计划任务执行完毕,执行时间为{}ms", "UserJob", System.currentTimeMillis() - start);
    }

    private void shutdown(ExecutorService executor) {
        try {
            // 关闭线程池
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            log.error("关闭线程池发生异常", e);
            Thread.currentThread().interrupt();
        }
    }

}
