package com.srw.common.annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2020/12/31 17:10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OrderHandlerType {
    String source();
}
