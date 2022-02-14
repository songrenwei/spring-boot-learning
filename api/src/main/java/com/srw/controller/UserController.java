package com.srw.controller;

import com.srw.business.service.UserService;
import com.srw.common.bean.JsonResult;
import com.srw.mock.QRcode;
import com.srw.persistence.entity.User;
import com.srw.persistence.mongodb.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2020/10/13
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final QRcode qRcode;

    @GetMapping("/findList")
    public JsonResult<?> findList() {
        return JsonResult.success(userService.findList());
    }

    @GetMapping("/query/{id}")
    public JsonResult<?> query(@PathVariable("id") Long id) {
        return JsonResult.success(userService.query(id));
    }

    @PostMapping("/create")
    public JsonResult<?> create(@RequestBody User user) {
        return JsonResult.success(userService.save(user));
    }

    @PostMapping("/delete")
    public JsonResult<?> delete(User user) {
        return JsonResult.success(userService.delete(user));
    }

    @PostMapping("/update")
    public JsonResult<?> update(@RequestBody User user) {
        return JsonResult.success(userService.update(user));
    }

    /**---------------------------------------MongoDB------------------------------------------**/
    @PostMapping("/save")
    public JsonResult<?> save(@RequestBody UserInfo userInfo) {
        return JsonResult.success(userService.save(userInfo));
    }

    @GetMapping("/list")
    public JsonResult<?> list() {
        return JsonResult.success(userService.list());
    }


    @DeleteMapping("/remove")
    public JsonResult<?> remove(Long id) {
        return JsonResult.success(userService.remove(id));
    }

    /**---------------------------------------qrcode------------------------------------------**/
    @GetMapping("/qrcode")
    public void qrcode(HttpServletResponse response) {
        qRcode.generateQRcode(response);
    }

    /**---------------------------------------export------------------------------------------**/
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        userService.export(response);
    }

    @GetMapping("/index")
    public ModelAndView thymeleaf() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name","thymeleaf");
        mv.setViewName("index.html");
        return mv;
    }

}
