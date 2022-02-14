package com.srw.controller;

import com.srw.common.bean.JsonResult;
import com.srw.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/3/31 17:30
 */
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/provider")
public class UserController {

    private final UserService userService;

    @GetMapping("/query")
    public JsonResult<List<Long>> query() {
        List<Long> list = userService.query();
        return JsonResult.success(list);
    }

}
