package com.srw.mq.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description: 工作模式是指向多个互相竞争的消费者发送消息的模式，它包含一个生产者、两个消费者和一个队列。
 * 两个消费者同时绑定到一个队列上去，当消费者获取消息处理耗时任务时，空闲的消费者从队列中获取并消费消息。
 * @Author: renwei.song
 * @Date: 2021/3/12 17:24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WorkSender {

    private final RabbitTemplate template;
    private static final String QUEUE_NAME = "work.hello";

    public void send(String message) {
        template.convertAndSend(QUEUE_NAME, message);
        log.info(" [work] Sent '{}'", message);
    }

}
