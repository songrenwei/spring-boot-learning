package com.srw.mq.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static java.lang.Math.*;

/**
 * @Description: 路由模式是可以根据路由键选择性给多个消费者发送消息的模式，它包含一个生产者、两个消费者、两个队列和一个交换机。
 * 两个消费者同时绑定到不同的队列上去，两个队列通过路由键绑定到交换机上去，生产者发送消息到交换机，交换机通过路由键转发到不同队列，
 * 队列绑定的消费者接收并消费消息。
 * @Author: renwei.song
 * @Date: 2021/3/15 18:05
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DirectSender {

    private final RabbitTemplate template;
    private static final String EXCHANGE_NAME = "exchange.direct";
    private final String[] keys = {"dog", "cat", "pig"};

    public void send(String message) {
        int random = (int)(random()*3);
        template.convertAndSend(EXCHANGE_NAME, keys[random], message+"-"+keys[random]);
        log.info(" [Direct] Sent '{}'", message);
    }

}
