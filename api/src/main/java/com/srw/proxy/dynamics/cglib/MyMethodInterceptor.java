package com.srw.proxy.dynamics.cglib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description: 委托类方法拦截器
 * @Author: renwei.song
 * @Date: 2021/3/17 17:36
 */
@Slf4j
public class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        log.info("卖房前...");
        // 注意这里的方法调用，不是用反射哦！！！
        Object object = proxy.invokeSuper(obj, args);
        log.info("卖房后...");
        return object;
    }

}
