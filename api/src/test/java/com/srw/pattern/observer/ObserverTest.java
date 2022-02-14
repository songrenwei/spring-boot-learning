package com.srw.pattern.observer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: 观察者模式
 * @Author: renwei.song
 * @Date: 2021/4/25 14:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObserverTest {

    @Test
    public void test() {
        Subject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserver1());
        subject.registerObserver(new ConcreteObserver2());
        subject.notifyObservers("发布消息...");
    }

    @Test
    public void test2() {
        // 省去具体的而观察者
        Subject subject = new ConcreteSubject();
        subject.registerObserver(msg -> System.out.println("我是观察者1, 消息："+msg));
        subject.registerObserver(msg -> System.out.println("我是观察者2, 消息："+msg));
        subject.notifyObservers("发布消息2...");
    }

}
