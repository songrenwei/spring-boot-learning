package com.srw.test.service.impl;

import com.srw.test.service.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/11/11 18:58
 */
@Slf4j
@Service
public class RetryServiceImpl implements RetryService {

    @Override
    public void test() {
        ((RetryServiceImpl)AopContext.currentProxy()).testRetry();
    }

    @Override
    @Async("serviceExecutor")
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 3000, multiplier = 1.5, maxDelay = 10000))
    public void testRetry() {
        System.out.println("开始执行方法。。。。。。。");
        int a = 1 / 0;
    }

    @Recover
    public void recover(Exception e) {
        System.out.println("回调方法执行！！！");
        log.error("异常：", e);
    }

}
