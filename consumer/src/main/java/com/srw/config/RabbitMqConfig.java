package com.srw.config;

import com.srw.common.enums.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: RabbitMQ五种消息模式
 * @Author: renwei.song
 * @Date: 2021/3/12 16:57
 */
@Configuration
public class RabbitMqConfig {

    //*****************************简单模式**************************
    @Bean
    public Queue hello() {
        return new Queue("simple.hello");
    }

    //*****************************工作模式**************************
    @Bean
    public Queue workQueue() {
        return new Queue("work.hello");
    }

    //*****************************发布/订阅模式**************************
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("exchange.fanout");
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.hello.1");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.hello.2");
    }

    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanout());
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanout());
    }

    //*****************************路由模式**************************
    @Bean
    public DirectExchange direct() {
        return new DirectExchange("exchange.direct");
    }

    @Bean
    public Queue directQueue1() {
        return new Queue("direct.hello.1");
    }

    @Bean
    public Queue directQueue2() {
        return new Queue("direct.hello.2");
    }

    @Bean
    public Binding directBinding1a() {
        return BindingBuilder.bind(directQueue1()).to(direct()).with("cat");
    }

    @Bean
    public Binding directBinding1b() {
        return BindingBuilder.bind(directQueue1()).to(direct()).with("dog");
    }

    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(directQueue2()).to(direct()).with("pig");
    }

    //*****************************通配符模式**************************

    @Bean
    public TopicExchange topic() {
        return new TopicExchange("exchange.topic");
    }

    @Bean
    public Queue topicQueue1() {
        return new Queue("topic.hello.1");
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("topic.hello.2");
    }

    @Bean
    public Binding topicBinding1a() {
        return BindingBuilder.bind(topicQueue1()).to(topic()).with("*.orange.*");
    }

    @Bean
    public Binding topicBinding1b() {
        return BindingBuilder.bind(topicQueue1()).to(topic()).with("*.*.rabbit");
    }

    @Bean
    public Binding topicBinding2a() {
        return BindingBuilder.bind(topicQueue2()).to(topic()).with("lazy.#");
    }

    //*****************************RabbitMQ 声明死信队列延时消费**************************
    /**
     * 订单消息实际消费队列所绑定的交换机（死信交换机）
     */
    @Bean
    DirectExchange orderDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 订单延迟队列所绑定的交换机
     */
    @Bean
    DirectExchange orderTtlDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 订单实际消费队列（死信队列）
     */
    @Bean
    public Queue orderQueue() {
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getQueue());
    }

    /**
     * 订单延迟队列
     */
    @Bean
    public Queue orderTtlQueue() {
        return QueueBuilder
                .durable(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getQueue())
                .withArgument("x-dead-letter-exchange", QueueEnum.QUEUE_ORDER_CANCEL.getExchange()) // 到期后转发的交换机
                .withArgument("x-dead-letter-routing-key", QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey()) // 到期后转发的路由键
                .build();
    }

    /**
     * 将订单队列绑定到交换机
     */
    @Bean
    Binding orderBinding(){
        return BindingBuilder
                .bind(orderQueue())
                .to(orderDirect())
                .with(QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey());
    }

    /**
     * 将订单延迟队列绑定到交换机
     */
    @Bean
    Binding orderTtlBinding(){
        return BindingBuilder
                .bind(orderTtlQueue())
                .to(orderTtlDirect())
                .with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey());
    }

}
