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
 * Created by macro on 2020/5/19.
 */
@Slf4j
@Component
@RabbitListener(queues = "work.hello")
public class Work2Receiver {

    @RabbitHandler
    public void receive(String in, Channel channel, Message message) throws IOException, InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
        // 确认收到一条消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        log.info(" [Work2] Received '{}'", in);
    }

}
