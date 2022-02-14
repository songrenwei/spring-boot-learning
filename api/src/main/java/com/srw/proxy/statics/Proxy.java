package com.srw.proxy.statics;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 代理类
 * @Author: renwei.song
 * @Date: 2021/3/17 16:52
 */
@Slf4j
public class Proxy implements Subject {

    private Subject realSubject;

    public Proxy(Subject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        log.info("卖房前。。。");
        realSubject.request();
        log.info("卖房后。。。");
    }

}
