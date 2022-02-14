package com.srw.pattern.observer.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Description: 事件
 * @Author: renwei.song
 * @Date: 2021/4/25 9:53
 */
public class MyEvent extends ApplicationEvent {

    private static final long serialVersionUID = -5718942517834412202L;

    public MyEvent(Object source) {
        super(source);
    }

}
