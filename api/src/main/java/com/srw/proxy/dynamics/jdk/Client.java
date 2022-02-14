package com.srw.proxy.dynamics.jdk;

/**
 * @Description: 调用方
 * @Author: renwei.song
 * @Date: 2021/3/17 16:52
 */
public class Client {

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        ProxySubject proxySubject = new ProxySubject(realSubject);
        Subject proxy = (Subject)proxySubject.getProxySubject();
        proxy.request();
    }

}
