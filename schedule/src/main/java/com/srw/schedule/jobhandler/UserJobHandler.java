package com.srw.schedule.jobhandler;

import com.srw.schedule.client.ExecuteClient;
import com.srw.schedule.client.QueryClient;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/3/31 16:26
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class UserJobHandler {

    private final QueryClient queryClient;
    private final ExecuteClient executeClient;
    private final JobTemplate<Long> jobTemplate;

    @XxlJob("UserJobHandler")
    public void executeUserJob() {
        jobTemplate.execute(queryClient::query, executeClient::execute);
        XxlJobHelper.handleSuccess();
    }

}
