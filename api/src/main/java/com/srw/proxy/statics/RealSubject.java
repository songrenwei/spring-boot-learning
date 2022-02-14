package com.srw.proxy.statics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 委托类
 * @Author: renwei.song
 * @Date: 2021/3/17 16:52
 */
@Slf4j
@RequiredArgsConstructor
public class RealSubject implements Subject {

    @Override
    public void request() {
        log.info("卖房。。。");
    }

}
