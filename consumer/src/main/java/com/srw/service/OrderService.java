package com.srw.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * 订单Service
 */
public interface OrderService {

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);

}
