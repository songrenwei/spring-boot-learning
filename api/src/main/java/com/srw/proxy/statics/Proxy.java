package com.srw.proxy.statics;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 代理类
 * @Author: renwei.song
 * @Date: 2021/3/17 16:52
 */
@Slf4j
public class Proxy implements Subject {

    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        log.info("卖房前。。。");
        subject.request();
        log.info("卖房后。。。");
    }

}
