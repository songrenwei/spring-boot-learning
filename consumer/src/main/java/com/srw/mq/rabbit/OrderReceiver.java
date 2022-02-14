package com.srw.mq.rabbit;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description: 正常处理订单
 * @Author: renwei.song
 * @Date: 2021/3/16 16:08
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RabbitListener(queues = "order.cancel.ttl")
public class OrderReceiver {

    @RabbitHandler
    public void handle(Channel channel, Long orderId, Message message) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        log.info("已正常处理订单， orderId:{}",orderId);
    }

}
