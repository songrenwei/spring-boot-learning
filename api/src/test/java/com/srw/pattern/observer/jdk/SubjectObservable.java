package com.srw.pattern.observer.jdk;

import org.springframework.stereotype.Component;

import java.util.Observable;

/**
 * @Description: 目标发布消息
 * @Author: renwei.song
 * @Date: 2021/4/13 9:34
 */
@Component
public class SubjectObservable extends Observable {

    /**
     * 发布消息
     *
     * @param message
     */
    public void publish(String message){

        // 改变状态
        this.setChanged();

        // 通知所有观察者
        this.notifyObservers(message);
    }

}
