package com.srw.schedule.jobhandler;

import com.srw.schedule.service.MailService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: 发邮件定时任务
 * @Author: songrenwei
 * @Date: 2020/10/21/15:58
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MailJobHandler {

    private final MailService mailService;

    @XxlJob("MailJobHandler")
    public void myJobHandler() {
        mailService.sendMail();
        XxlJobHelper.handleSuccess();
    }

}
