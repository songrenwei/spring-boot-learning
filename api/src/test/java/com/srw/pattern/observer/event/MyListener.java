package com.srw.pattern.observer.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationListener;

/**
 * @Description: 事件监听器
 * @Author: renwei.song
 * @Date: 2021/4/25 9:55
 */
@Data
@AllArgsConstructor
public class MyListener implements ApplicationListener<MyEvent> {

    private String name;

    @Override
    public void onApplicationEvent(@NotNull MyEvent event) {
        receive(event);
    }

    private void receive(MyEvent event) {
        String msg = (String) event.getSource();
        System.out.printf("我是：%s，消息：%s\n", this.getName(), msg);
    }

}
