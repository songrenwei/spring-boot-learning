package com.srw.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newFixedThreadPool;

@Configuration
@Slf4j
public class ThreadPoolConfig {

    @Bean("taskExecutor")
    public ExecutorService executor() {
        int nThread = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = newFixedThreadPool(nThread * 2);
        addShutdownHook(executorService);
        return executorService;
    }

    private void addShutdownHook(ExecutorService executorService) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            executorService.shutdown();
            try {
                executorService.awaitTermination(5, TimeUnit.MINUTES);
                log.info("TaskExecutor closed.");
            } catch (InterruptedException e) {
                log.warn("InterruptedException on destroy()", e);
            }
        }, "TaskExecutorShutDownHook"));
    }
}
