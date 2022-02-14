package com.srw.proxy.dynamics.cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @Description: 调用方
 * @Author: renwei.song
 * @Date: 2021/3/17 16:52
 */
public class Client {

    public static void main(String[] args) {
        // 创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
        Enhancer enhancer = new Enhancer();
        // 设置目标类的字节码文件
        enhancer.setSuperclass(RealSubject.class);
        // 设置回调函数
        enhancer.setCallback(new MyMethodInterceptor());

        // 这里的creat方法就是正式创建代理类（委托类的子类）即：com.srw.proxy.dynamics.cglib.RealSubject$$EnhancerByCGLIB$$a692c911@7fe8ea47
        RealSubject proxy = (RealSubject) enhancer.create();
        // 重写委托类非final（final方法不能被重写）方法后的方法
        proxy.request();
    }

}
