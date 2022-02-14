package com.srw.test.service.impl;

import com.srw.common.annotation.DistributedLock;
import com.srw.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Override
    @DistributedLock(key = "#name", failMessage = "obtain lock is failure") // 使用 spel 表达式
    public void lockTest(String name) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
    }

}
