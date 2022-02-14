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
 * @Date: 2021/3/15 13:51
 */
@Slf4j
@Component
@RabbitListener(queues = "fanout.hello.1")
public class Fanout1Receiver {

    @RabbitHandler
    public void receive(String in, Channel channel, Message message) throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        log.info(" [Fanout1] Received '{}'", in);
    }

}
