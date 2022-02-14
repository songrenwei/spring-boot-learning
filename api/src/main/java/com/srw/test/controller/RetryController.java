package com.srw.test.controller;

import com.srw.test.service.RetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/4/27 15:30
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class RetryController {

    private final RetryService retryService;

    @GetMapping("/retry")
    public void retry() {
        System.out.println("开始调用方法！！！");
        retryService.test();
        System.out.println("调用方法结束！！！");
    }

}
