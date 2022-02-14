package com.srw.pattern.strategy;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2020/12/31 17:08
 */
@Data
public class Order {
    /**
     * 订单来源
     */
    private String source;
    /**
     * 支付方式
     */
    private String payMethod;
    /**
     * 订单编号
     */
    private String code;
    /**
     * 订单金额
     */
    private BigDecimal amount;
    // ...其他的一些字段


    public Order(String source) {
        this.source = source;
    }
}
