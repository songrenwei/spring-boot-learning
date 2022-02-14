package com.srw.service.impl;

import com.srw.common.dto.OrderParam;
import com.srw.mq.rabbit.CancelOrderSender;
import com.srw.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.result.R;
import org.springframework.stereotype.Service;

/**
 * 订单Service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CancelOrderSender cancelOrderSender;

    @Override
    public R<?> generateOrder(OrderParam orderParam) {
        // 下单完成后开启一个延迟消息，用于当用户没有付款时取消订单（orderId应该在下单后生成）
        log.info("下单成功， orderId:{}", 11L);
        sendDelayMessageCancelOrder(11L);
        return R.success("下单成功");
    }

    private void sendDelayMessageCancelOrder(Long orderId) {
        // 获取订单超时时间，假设为60分钟(测试用的30秒)
        long delayTimes = 30 * 1000;
        // 发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }

}
