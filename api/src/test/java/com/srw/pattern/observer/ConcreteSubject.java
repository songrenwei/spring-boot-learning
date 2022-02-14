package com.srw.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 具体目标对象
 * @Author: renwei.song
 * @Date: 2021/4/25 14:39
 */
public class ConcreteSubject implements Subject {

    List<Observer> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void notifyObservers(String msg) {
        for (Observer o : observerList) {
            o.notify(msg);
        }
    }

}
