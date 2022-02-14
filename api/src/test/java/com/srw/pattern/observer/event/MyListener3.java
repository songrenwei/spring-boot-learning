package com.srw.pattern.observer.event;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 事件监听器
 * @Author: renwei.song
 * @Date: 2021/4/25 9:55
 */
@Component
public class MyListener3 implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(@NotNull MyEvent event) {
        receive(event);
    }

    private void receive(MyEvent event) {
        String msg = (String) event.getSource();
        System.out.printf("我是：%s，消息：%s\n", "MyListener3", msg);
    }

}
