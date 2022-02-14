package com.srw.controller;

import com.srw.common.bean.JsonResult;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/metrics")
public class MetricsController {

    private final MeterRegistry registry;

    private Counter coreCounter;
    private Counter indexCounter;
    private AtomicInteger app_online_count;

    @PostConstruct
    private void init(){
        coreCounter = registry.counter("app_requests_method_count", "method", "MetricsController.core");
        indexCounter = registry.counter("app_requests_method_count", "method", "MetricsController.index");
        app_online_count = registry.gauge("app_online_count", new AtomicInteger(0));
    }

    @RequestMapping(value = "/index")
    public JsonResult<String> index(){
        indexCounter.increment();

        return JsonResult.success(indexCounter.count() + " index of api.");
    }

    @RequestMapping(value = "/core")
    public JsonResult<String> coreUrl(){
        coreCounter.increment();

        return JsonResult.success(coreCounter.count() + " coreUrl Monitor by Prometheus.");
    }

    @RequestMapping(value = "/online")
    public JsonResult<String> onlineCount(){
        int count = new Random().nextInt(100);
        app_online_count.set(count);
        log.info("current online count: " + count);
        return JsonResult.success("current online count: " + count);
    }
}

