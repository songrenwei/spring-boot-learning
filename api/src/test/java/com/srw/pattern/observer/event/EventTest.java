package com.srw.pattern.observer.event;

import com.srw.persistence.entity.User;
import com.srw.persistence.enums.SexEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/4/25 10:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventTest {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ApplicationEventPublisher publisher;

    @Test
    public void test() {
        applicationContext.publishEvent(new MyEvent("我是消息"));
    }

    @Test
    public void test2() {
        User user = new User();
        user.setUsername("易居");
        user.setSex(SexEnum.MALE);
        publisher.publishEvent(user);
        publisher.publishEvent(new MyEvent("我是消息2"));
    }

}
