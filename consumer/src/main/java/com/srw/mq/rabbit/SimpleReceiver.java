package com.srw.mq.rabbit;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 消息接收者
 * @Author: renwei.song
 * @Date: 2021/3/12 17:05
 */
@Slf4j
@Component
@RabbitListener(queues = "simple.hello")
public class SimpleReceiver {

    @RabbitHandler
    public void receive(String in, Channel channel, Message message) throws InterruptedException, IOException {
        TimeUnit.SECONDS.sleep(1);
        try {
            // 消息接收成功确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info(" [Simple] Received '{}'", in);
        } catch (Exception e) {
            // 消息接收失败确认
            /*
             * deliveryTag:该消息的index
             * multiple：是否批量,true:将一次性拒绝所有小于deliveryTag的消息
             * requeue：被拒绝的消息是否重新入队列
             */
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

}
