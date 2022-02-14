package com.srw.pattern.observer.jdk;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Observable;
import java.util.Observer;

/**
 * @Description: 观察者订阅消息
 * @Author: renwei.song
 * @Date: 2021/4/13 9:37
 */
@Data
@AllArgsConstructor
public class ConcreteObserver implements Observer {

    private String name;

    @Override
    public void update(Observable o, Object arg) {
        // 更新文章
        updateArticle(o, arg);
    }

    private void updateArticle(Observable o, Object message) {
        System.out.println(String.format("我是读者：%s，文章已更新为：%s\n", this.getName(), message));
    }

}
