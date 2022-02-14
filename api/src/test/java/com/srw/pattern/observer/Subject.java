package com.srw.pattern.observer;

/**
 * @Description: 目标对象
 * @Author: renwei.song
 * @Date: 2021/4/25 14:36
 */
public interface Subject {

    /**
     * 注册观察者
     *
     * @param o
     */
    void registerObserver(Observer o);

    /**
     * 通知观察者
     *
     * @param msg
     */
    void notifyObservers(String msg);

}
