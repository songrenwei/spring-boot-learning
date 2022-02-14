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
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/3/15 18:06
 */
@Slf4j
@Component
@RabbitListener(queues = "direct.hello.1")
public class Direct1Receiver {

    @RabbitHandler
    public void receive(String in, Channel channel, Message message) throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        log.info(" [Direct1] Received '{}'", in);
    }

}
