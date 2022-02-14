package com.srw.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {

    /**
     * 分布式锁key
     */
    String key();

    /**
     * 获取锁失败提示
     */
    String failMessage() default "";

}
