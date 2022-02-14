package com.srw.proxy.dynamics.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * @Description: 代理类
 * @Author: renwei.song
 * @Date: 2021/3/17 16:52
 */
@Slf4j
public class ProxySubject {

    // 委托类
    private Subject target;

    public ProxySubject(Subject target) {
        this.target = target;
    }

    // 为目标对象生成代理对象
    public Object getProxySubject() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    log.info("卖房前...");
                    // 执行目标对象方法
                    method.invoke(target, args);
                    log.info("卖房后...");
                    return null;
                });
    }

}
