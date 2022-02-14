package com.srw.controller;

import net.dreamlu.mica.core.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/8/25 17:04
 */
@RestController
@RequestMapping("/provider")
public class TestController {

    @GetMapping("/test1")
    public R<?> test1() throws InterruptedException {
        System.out.println("===========provider begin=============");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("===========provider end=============");
        return R.success();
    }

}
