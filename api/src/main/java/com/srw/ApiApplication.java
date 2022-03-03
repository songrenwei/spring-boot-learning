package com.srw;

import com.dtflys.forest.springboot.annotation.ForestScan;
import com.srw.autoconfigure.EnableSrw;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

//@EnableSrw
@EnableAsync
@EnableRetry
@ForestScan(basePackages = "com.srw.client")
@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
