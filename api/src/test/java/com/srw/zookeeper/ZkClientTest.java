package com.srw.zookeeper;

import com.srw.ApiApplication;
import org.springframework.boot.SpringApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ZkClientTest {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 模拟多个客户端获取配置
        executorService.submit(new ZkConfigClient());
        executorService.submit(new ZkConfigClient());
        executorService.submit(new ZkConfigClient());
    }

}
