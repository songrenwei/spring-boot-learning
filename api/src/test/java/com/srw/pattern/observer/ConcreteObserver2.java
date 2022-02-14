package com.srw.pattern.observer;

/**
 * @Description: 具体观察者
 * @Author: renwei.song
 * @Date: 2021/4/25 14:34
 */
public class ConcreteObserver2 implements Observer{

    @Override
    public void notify(String msg) {
        System.out.println("我是观察者2, 消息："+msg);
    }

}
