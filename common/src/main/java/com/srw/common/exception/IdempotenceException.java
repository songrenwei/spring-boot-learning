package com.srw.common.exception;

/**
 * @Description: 幂等异常
 * @Author: songrenwei
 * @Date: 2020/12/10/11:23
 */
public class IdempotenceException extends BaseException {

    private static final long serialVersionUID = -3494242999625040736L;

    public IdempotenceException(String errorMessage) {
        super(errorMessage);
    }
}
