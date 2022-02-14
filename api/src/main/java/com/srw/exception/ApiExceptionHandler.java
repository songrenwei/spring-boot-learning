package com.srw.exception;

import com.srw.common.bean.JsonResult;
import com.srw.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description: 统一异常处理
 * @Author: songrenwei
 * @Date: 2020/10/14/15:07
 */
@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public JsonResult<?> handleException(Exception e) {
        log.error("抛出未捕获异常:", e);
        return JsonResult.error(e.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public JsonResult<?> handleBusinessException(BaseException e) {
        log.error("抛出自定义服务异常:", e);
        if (e.getErrorCode() != null) {
            return JsonResult.error(e.getErrorCode(), e.getMessage());
        } else {
            return JsonResult.error(e.getMessage());
        }
    }

}
