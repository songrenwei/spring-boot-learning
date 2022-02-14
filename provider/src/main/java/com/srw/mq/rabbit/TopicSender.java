package com.srw.mq.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description: 通配符模式是可以根据路由键匹配规则选择性给多个消费者发送消息的模式，它包含一个生产者、两个消费者、两个队列和一个交换机。
 * 两个消费者同时绑定到不同的队列上去，两个队列通过路由键匹配规则绑定到交换机上去，生产者发送消息到交换机，交换机通过路由键匹配规则转发到
 * 不同队列，队列绑定的消费者接收并消费消息。
 * @Author: renwei.song
 * @Date: 2021/3/16 11:16
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TopicSender {

    private final RabbitTemplate template;
    private static final String EXCHANGE_NAME = "exchange.topic";
    private final String[] keys =
            {"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox", "1.orange.1", "1.1.rabbit", "lazy.123.123", "lazy."};

    public void send(int index, String message) {
        template.convertAndSend(EXCHANGE_NAME, keys[index], message+"-"+keys[index]);
        log.info(" [Topic] Sent '{}'", message);
    }

}
