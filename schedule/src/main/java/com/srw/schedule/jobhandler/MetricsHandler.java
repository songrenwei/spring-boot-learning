package com.srw.schedule.jobhandler;

import com.srw.schedule.client.QueryClient;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/7/2 16:26
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MetricsHandler {

    private final QueryClient queryClient;

    @XxlJob("MetricsJobHandler")
    public void executeUserJob() {
        XxlJobHelper.log("{}计划任务执行开始", "MetricsJobHandler");
        queryClient.metrics();
        XxlJobHelper.handleSuccess();
    }

}
