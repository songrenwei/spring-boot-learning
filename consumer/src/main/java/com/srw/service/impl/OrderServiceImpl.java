package com.srw.service.impl;

import com.srw.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/3/16 16:28
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public void cancelOrder(Long orderId) {
        log.info("已取消超时订单， orderId:{}",orderId);
    }

}
