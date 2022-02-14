package com.srw.pattern.observer.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 配置监听器
 * @Author: renwei.song
 * @Date: 2021/4/25 9:57
 */
@Configuration
public class ListenerConfig {

    @Bean
    public MyListener listener1(){
        return new MyListener("小明");
    }

    @Bean
    public MyListener listener2(){
        return new MyListener("小张");
    }

    @Bean
    public MyListener listener3(){
        return new MyListener("小爱");
    }

}
