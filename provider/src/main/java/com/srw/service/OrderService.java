package com.srw.service;

import com.srw.common.dto.OrderParam;
import net.dreamlu.mica.core.result.R;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单Service
 */
public interface OrderService {

    /**
     * 根据提交信息生成订单
     */
    @Transactional
    R<?> generateOrder(OrderParam orderParam);

}
