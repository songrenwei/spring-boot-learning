package com.srw.test.service.impl;

import com.srw.test.service.AsyncService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/11/23 14:06
 */
@Service
//@Transactional
public class AsyncServiceImpl implements AsyncService {

    @Qualifier("serviceExecutor")
    @Autowired
    private ThreadPoolTaskExecutor executor;
    
    @Override
    public void test() {
//        AsyncService asyncService = ((AsyncService)AopContext.currentProxy());
//        asyncService.test2();
        String name = "异步方法";
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> testAsync(name), executor);
        System.out.println(future.join());
    }

    @Override
    @Async("serviceExecutor")
    public void test2() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test2。。。");
    }

    @Async("serviceExecutor")
    public String testAsync(String name) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "testAsync。。。"+name;
    }

}
