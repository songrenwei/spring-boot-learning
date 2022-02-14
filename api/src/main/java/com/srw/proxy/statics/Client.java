package com.srw.proxy.statics;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 调用方
 * @Author: renwei.song
 * @Date: 2021/3/17 16:52
 */
@Slf4j
public class Client {

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        Proxy proxy = new Proxy(realSubject);
        proxy.request();
    }

}
