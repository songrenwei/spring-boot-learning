package com.srw.mq.rabbit;

import com.srw.common.annotation.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description: 简单模式是最简单的消息模式，它包含一个生产者、一个消费者和一个队列。生产者向队列里发送消息，消费者从队列中获取消息并消费
 * @Author: renwei.song
 * @Date: 2021/3/12 17:04
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SimpleSender {

    private final RabbitTemplate template;
    private static final String QUEUE_NAME = "simple.hello";

    public void send(String message) {
        // 设置生产者消息publish-confirm回调函数
        template.setConfirmCallback((correlationData, ack, cause) -> {
//            assert correlationData != null;
//            log.info("返回消息id：{}", correlationData.getId());
            if (ack) {
                log.info("消息发送确认成功");
            } else {
                log.info("消息发送确认失败:" + cause);
            }
        });

        // 设置生产者消息publisher-returns回调函数
        template.setReturnCallback((msg, replyCode, replyText, exchange, routingKey) -> log.info("回退消息：{}", msg));
        template.setMandatory(true); // 设置强制标志 仅适用于回退模式

        template.convertAndSend(QUEUE_NAME, message);
        log.info(" [Simple] Sent '{}'", message);
    }

}
