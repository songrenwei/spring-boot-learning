package com.srw.completablefuture;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/8/26 13:41
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompletableFutureTest {

    private int nThread = Runtime.getRuntime().availableProcessors();

    @Autowired
    private RestTemplate restTemplate;

    // 调用第三方api获取地址名称
    private String getAddress(String latitude, String longitude) {
        StopWatch sw = new StopWatch();
        sw.start();
        JSONObject result = restTemplate.getForObject("http://ditu.amap.com/service/regeo?latitude={1}&longitude={2}",
                JSONObject.class, latitude, longitude);

        sw.stop();
        // 记录执行线程以及耗时时间
        System.out.println(String.format("getAddress: %s -> 耗时: %s", Thread.currentThread(), sw.getTotalTimeMillis()));

        if (result.getInt("status") == 1) {
            return result.getJSONObject("data").getStr("desc");
        }
        return null;
    }


    @Test
    public void test() {
        String[] cities = {
                "31.2363429624,121.4803295328",
                "39.9110666857,116.4136103013",
                "30.2799186759,120.1617445782",
                "31.8880209204,117.3066537271",
                "25.6122215609,100.2742019952",
                "26.8624428068,100.2335674911",
                "27.6958640000,111.7206640000",
                "31.2093160000,112.4105620000",
                "39.1731490000,117.2202970000",
                "34.5113900000,101.5563070000",
                "30.5984670000,114.3115860000"
        };

        List<Long> times = new ArrayList<>();

        // 调用20次，计算调用11个经纬度耗时时间
        IntStream.rangeClosed(1, 20)
                .forEach(i -> {
                    StopWatch sw = new StopWatch();
                    sw.start();

                    // 调用实现的方法，更改不同的方法，统计时间
                    List<String> r = test3(cities);
                    System.out.println(r);
                    sw.stop();
                    times.add(sw.getTotalTimeMillis());
                });

        // 耗时统计
        LongSummaryStatistics summaryStatistics = times.stream().mapToLong(Long::longValue).summaryStatistics();
        System.out.println(times);
        System.out.println("平均耗时: " + summaryStatistics);
    }

    // 方法一 普通循环调用
    private List<String> test1(String[] locations) {
        return Arrays.stream(locations)
                .filter(city -> city.split(",").length == 2)
                .map(city -> {
                    String[] v = city.split(",");
                    return getAddress(v[0], v[1]);
                })
                .collect(Collectors.toList());
    }

    // java8 Stream并行调用
    private List<String> test2(String[] locations) {
        return Arrays.stream(locations).parallel()
                .filter(city -> city.split(",").length == 2)
                .map(city -> {
                    String[] v = city.split(",");
                    return getAddress(v[0], v[1]);
                })
                .collect(Collectors.toList());
    }

    // java8 CompletableFuture
    private List<String> test3(String[] locations) {
        ExecutorService executor = Executors.newFixedThreadPool(Math.min(locations.length, nThread * 2));
        List<CompletableFuture<String>> collect = Arrays.stream(locations)
                .filter(city -> city.split(",").length == 2)
                .map(city -> CompletableFuture.supplyAsync(() -> {
                    String[] v = city.split(",");
                    return getAddress(v[0], v[1]);
                }, executor))
                .collect(Collectors.toList());

        return collect.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }

}
