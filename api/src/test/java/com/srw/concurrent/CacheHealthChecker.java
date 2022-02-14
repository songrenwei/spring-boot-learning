package com.srw.concurrent;

import java.util.concurrent.CountDownLatch;

public class CacheHealthChecker extends BaseHealthChecker {

    public CacheHealthChecker (CountDownLatch latch) {
        super("Cache Service", latch);
    }

    @Override
    public void verifyService() {
        System.out.println("Checking " + this.getServiceName() + " ...");
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}
