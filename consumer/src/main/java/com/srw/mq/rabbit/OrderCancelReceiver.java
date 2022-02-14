package com.srw.mq.rabbit;

import com.rabbitmq.client.Channel;
import com.srw.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description: 取消订单
 * @Author: renwei.song
 * @Date: 2021/3/16 16:08
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RabbitListener(queues = "order.cancel")
public class OrderCancelReceiver {

    private final OrderService orderService;

    @RabbitHandler
    public void handle(Long orderId, Channel channel, Message message) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        log.info("已收到超时消息， orderId:{}",orderId);
        orderService.cancelOrder(orderId);
    }

}
