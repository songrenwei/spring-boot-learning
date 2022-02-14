package com.srw.test.controller;

import com.srw.test.service.TestService;
import com.srw.common.bean.JsonResult;
import com.srw.common.component.ZkDistributeImproveLock;
import com.srw.common.component.ZkDistributeLock;
import com.srw.common.component.ZkLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/4/27 15:30
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class LockController {
    private final TestService testService;

    @GetMapping("/lock")
    public JsonResult<?> lock() throws InterruptedException {
        testService.lockTest("srw");
        return JsonResult.success("");
    }

    @GetMapping("/lock2")
    public JsonResult<?> lock2() throws InterruptedException {
        ZkDistributeLock lock = new ZkDistributeLock("/zkLock");
        lock.lock();
        System.out.println("执行业务逻辑...");
        TimeUnit.SECONDS.sleep(10);
        lock.unlock();
        System.out.println("...... 释放锁 ......");
        return JsonResult.success("成功返回");
    }

    @GetMapping("/lock3")
    public JsonResult<?> lock3() throws InterruptedException {
        ZkLock zkLock = new ZkLock("/zkLock");
        String lockName = zkLock.getLock();
        System.out.println("执行业务逻辑...");
        TimeUnit.SECONDS.sleep(15);
        zkLock.releaseLock(lockName);
        System.out.println("...... 释放锁 ......");

        return JsonResult.success(lockName);
    }

    @GetMapping("/lock4")
    public JsonResult<?> lock4() throws InterruptedException {
        ZkDistributeImproveLock lock = new ZkDistributeImproveLock("/zkLock");
        lock.lock();
        System.out.println("执行业务逻辑...");
        TimeUnit.SECONDS.sleep(20);
        lock.unlock();
        System.out.println("...... 释放锁 ......");
        return JsonResult.success("成功返回");
    }

}
