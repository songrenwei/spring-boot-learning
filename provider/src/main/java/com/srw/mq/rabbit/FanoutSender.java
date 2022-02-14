package com.srw.mq.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description: 发布/订阅模式是指同时向多个消费者发送消息的模式（类似广播的形式），它包含一个生产者、两个消费者、两个队列和一个交换机。
 * 两个消费者同时绑定到不同的队列上去，两个队列绑定到交换机上去，生产者通过发送消息到交换机，所有消费者接收并消费消息。
 * @Author: renwei.song
 * @Date: 2021/3/15 13:43
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class FanoutSender {

    private final RabbitTemplate template;

    private static final String EXCHANGE_NAME = "exchange.fanout";

    public void send(String message) {
        template.convertAndSend(EXCHANGE_NAME, StringUtils.EMPTY, message);
        log.info(" [Fanout] Sent '{}'", message);
    }

}
