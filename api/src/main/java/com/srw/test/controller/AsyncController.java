package com.srw.test.controller;

import com.srw.test.service.AsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/11/23 14:04
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class AsyncController {

    private final AsyncService asyncService;

    @GetMapping("/async")
    public void async() {
        System.out.println("开始调用方法！！！");
        asyncService.test();
        System.out.println("调用方法结束！！！");
    }

}
