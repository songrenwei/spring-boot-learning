package com.srw.handler;

import com.srw.common.bean.JsonResult;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public JsonResult<?> globalExceptionHandler(HttpServletRequest request, RuntimeException e) {
        e.printStackTrace();
        return JsonResult.error(e.getMessage());
    }

    @InitBinder
    public void globalInitBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    @ModelAttribute(value = "token")
    public String globalModelAttribute(HttpServletRequest request) {
        return request.getHeader("Token");
    }

}