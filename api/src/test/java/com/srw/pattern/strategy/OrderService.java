package com.srw.pattern.strategy;

import com.srw.common.annotation.OrderHandlerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2020/12/31 17:09
 */
@Service
public class OrderService {

    /**
     * 维护一个映射：来源 -> 这个来源的动作
     */
    private Map<String, OrderHandler> orderHandleMap;

    /**
     * 注入映射（这里用的是注入的方式，也可以用static静态代码块等）
     *
     * @param orderHandlers
     */
    @Autowired
    public void setOrderHandle(List<OrderHandler> orderHandlers) {
        // 注入各种类型的订单处理类
        orderHandleMap = orderHandlers.stream().collect(
                Collectors.toMap(orderHandler -> Objects.requireNonNull(AnnotationUtils.findAnnotation(orderHandler.getClass(), OrderHandlerType.class), "no such handlerType").source(),
                        v -> v, (v1, v2) -> v1));
    }

    public void handle(Order order) {
        // ...一些前置处理

        // 通过订单来源确定对应的handler
        OrderHandler orderHandler = orderHandleMap.get(order.getSource());
        orderHandler.handle(order);

        // ...一些后置处理
    }

    /**
     * 来一重载的方法
     *
     * @param order
     * @param consumer
     */
    public void handle(Order order, Consumer<Order> consumer) {
        consumer.accept(order);
    }

}