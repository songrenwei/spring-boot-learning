package com.srw.schedule;

import com.srw.common.bean.JsonResult;
import com.srw.schedule.client.ExecuteClient;
import com.srw.schedule.client.QueryClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/3/31 17:59
 */
@SpringBootTest
class ScheduleApplicationTest {

    @Resource
    private QueryClient queryClient;
    @Resource
    private ExecuteClient executeClient;

    @Test
    void test() {
        JsonResult<List<Long>> result = queryClient.query();
        List<Long> list = result.getData();
        for (Long id:list) {
            executeClient.execute(id);
        }
    }

    @Test
    void test2() {
        List<String> list = Arrays.asList("a", "b", "c");
        List<String> aList = list.stream().map(item -> item+item).collect(Collectors.toList());
        Object[] arrays = list.stream().map(item -> item+item).toArray();
        String[] aArray = list.stream().map(item -> item+item).toArray(String[]::new);

    }

    @Test
    void test3() {
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一个异步任务");
        });
//        f1.join();
        CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第二个异步任务");
        });
        f2.join();
//        CompletableFuture.allOf(f2,f1).join(); // 等待所有线程执行完毕, 可以阻塞主线程
        System.out.println("CompletableFuture Test runAsync");
    }

}