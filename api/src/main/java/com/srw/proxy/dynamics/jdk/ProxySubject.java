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
    private Subject subject;

    public ProxySubject(Subject subject) {
        this.subject = subject;
    }

    // 为目标对象生成代理对象
    public Object getProxySubject() {
        return Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    log.info("卖房前...");
                    // 执行目标对象方法
                    method.invoke(subject, args);
                    log.info("卖房后...");
                    return null;
                });
    }

}
