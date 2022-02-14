package com.srw.pattern.observer.event;

import com.srw.persistence.entity.User;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 事件监听器
 * @Author: renwei.song
 * @Date: 2021/4/25 9:55
 */
@Component
public class MyListener5 {

    @EventListener
    public void receive(User user) {
        System.out.println(user.getUsername());
    }

}
