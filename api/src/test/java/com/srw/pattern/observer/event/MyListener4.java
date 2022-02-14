package com.srw.pattern.observer.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 事件监听器
 * @Author: renwei.song
 * @Date: 2021/4/25 9:55
 */
@Component
public class MyListener4 {

    @EventListener
    public void receive(MyEvent event) {
        String msg = (String) event.getSource();
        System.out.printf("我是：%s，消息：%s\n", "MyListener4-1", msg);
    }

    @EventListener
    public void receive2(MyEvent event) {
        String msg = (String) event.getSource();
        System.out.printf("我是：%s，消息：%s\n", "MyListener4-2", msg);
    }

}
