package com.srw.common.exception;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2021/8/3 21:05
 */
public class HttpRequestException extends BaseException {

    private static final long serialVersionUID = -105607103183489858L;

    public HttpRequestException(String errorMessage) {
        super(errorMessage);
    }
}
