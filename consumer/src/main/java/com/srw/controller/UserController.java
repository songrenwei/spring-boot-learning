package com.srw.controller;

import com.srw.common.bean.JsonResult;
import com.srw.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/3/31 17:30
 */
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/consumer")
public class UserController {

    private final UserService userService;

    @GetMapping("/execute")
    public JsonResult<Boolean> execute(Long userId) {
        userService.execute(userId);
        return JsonResult.success(true);
    }

}
