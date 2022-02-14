package com.srw.mq.rabbit;

import com.srw.common.enums.QueueEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description: 取消订单消息的发出者
 * @Author: renwei.song
 * @Date: 2021/3/16 15:56
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CancelOrderSender {

    private final RabbitTemplate template;

    // 给延迟队列发送消息
    public void sendMessage(Long orderId, final long delayTimes){
        template.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(), QueueEnum.QUEUE_TTL_ORDER_CANCEL.getQueue(), orderId, message -> {
            // 给消息设置延迟毫秒值
            message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
            return message;
        });
        log.info("发送消息至延迟队列， orderId:{}",orderId);
    }

}
