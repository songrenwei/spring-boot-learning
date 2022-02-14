package com.srw.controller;

import com.srw.common.utils.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/8/25 17:04
 */
@Slf4j
@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class TestController {

    private final HttpUtils httpUtils;

    @GetMapping("/test1/{time}")
    public R<?> test1(@PathVariable("time") long time) {
        System.out.println("响应超时时间 = " + time);
        httpUtils.doGet("http://localhost:8082/provider/test1", time);
        return R.success();
    }

}
