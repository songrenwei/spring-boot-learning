package com.srw.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 常量类
 * @Author: songrenwei
 * @Date: 2020/10/14/15:15
 */
public class Constants {

    public static final long TEN_SECONDS = 10;

    public static final long THIRTY_SECONDS = 30;

    public static final long SIXTY_SECONDS = 60;

    public static final long ONE_HUNDRED_SECONDS = 100;

    /**
     * 响应成功code
     */
    public static final String RESULT_SUCCESS_CODE = "00000";
    /**
     * 响应错误code
     */
    public static final String RESULT_ERROR_CODE = "10001";

    /**
     * 自定义异常code
     */
    public static final Map<String, String> EXCEPTION_CODE_MAP = new HashMap<String, String>(100) {
        private static final long serialVersionUID = 5089868132306478837L;

        {
            put("BusinessException", "20000");
            put("IdempotenceException", "20001");
            put("LockException", "20002");
        }
    };
}
