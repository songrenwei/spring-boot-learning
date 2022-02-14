package com.srw.pattern.observer.jdk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/4/13 9:41
 */
@SpringBootTest
class ObserverTest {

    @Autowired
    private SubjectObservable subjectObservable;

    @Test
    void test() {

        // 添加观察者
        subjectObservable.addObserver(new ConcreteObserver("小明"));
        subjectObservable.addObserver(new ConcreteObserver("小张"));
        subjectObservable.addObserver(new ConcreteObserver("小爱"));

        // 发布消息
        subjectObservable.publish("什么是观察者模式？");
    }

}
