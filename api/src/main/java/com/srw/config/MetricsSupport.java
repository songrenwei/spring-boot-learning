package com.srw.config;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.srw.config.ExecutorConfig.SERVICE_EXECUTOR;

@Component
public class MetricsSupport {

    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    @Qualifier(SERVICE_EXECUTOR)
    private ThreadPoolTaskExecutor serviceExecutor;

    @PostConstruct
    public void init() {
        initServiceExecutorMetrics(serviceExecutor, "executor.service");
    }

    /**
     * 线程池metrics指标监控
     *
     * @param serviceExecutor 线程池
     * @param namePrefix 指标名称前缀
     */
    private void initServiceExecutorMetrics(ThreadPoolTaskExecutor serviceExecutor, String namePrefix) {
        Gauge.builder(namePrefix + ".active", serviceExecutor, ThreadPoolTaskExecutor::getActiveCount)
                .register(meterRegistry);
        Gauge.builder(namePrefix + ".core", serviceExecutor, ThreadPoolTaskExecutor::getCorePoolSize)
                .register(meterRegistry);
        Gauge.builder(namePrefix + ".max", serviceExecutor, ThreadPoolTaskExecutor::getMaxPoolSize)
                .register(meterRegistry);
        Gauge.builder(namePrefix + ".pool", serviceExecutor, ThreadPoolTaskExecutor::getPoolSize)
                .register(meterRegistry);
        Gauge.builder(namePrefix + ".queue", serviceExecutor, executor -> executor.getThreadPoolExecutor().getQueue().size())
                .register(meterRegistry);
        Gauge.builder(namePrefix + ".task", serviceExecutor, executor -> executor.getThreadPoolExecutor().getTaskCount())
                .register(meterRegistry);
        Gauge.builder(namePrefix + ".complete", serviceExecutor, executor -> executor.getThreadPoolExecutor().getCompletedTaskCount())
                .register(meterRegistry);
    }
}
