package com.srw.controller;

import com.srw.common.dto.OrderParam;
import com.srw.mq.rabbit.*;
import com.srw.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.result.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/3/12 17:10
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class RabbitController {

    private final SimpleSender simpleSender;
    private final WorkSender workSender;
    private final FanoutSender fanoutSender;
    private final DirectSender directSender;
    private final TopicSender topicSender;
    private final OrderService portalOrderService;

    @RequestMapping("/send/simple")
    public R<?> sendSimple() {
        for (int i = 0; i < 10; i++) {
            simpleSender.send("发他10个simple消息"+i);
        }
        return R.success("send.simple.ok");
    }

    @RequestMapping("/send/work")
    public R<?> sendWork() {
        for (int i = 0; i < 10; i++) {
            workSender.send("发他10个work消息"+i);
        }
        return R.success("send.work.ok");
    }

    @RequestMapping("/send/fanout")
    public R<?> sendFanout() {
        for (int i = 0; i < 10; i++) {
            fanoutSender.send("发他10个fanout消息"+i);
        }
        return R.success("send.fanout.ok");
    }

    @RequestMapping("/send/direct")
    public R<?> sendDirect() {
        for (int i = 0; i < 10; i++) {
            directSender.send("发他10个direct消息"+i);
        }
        return R.success("send.direct.ok");
    }

    @RequestMapping("/send/topic")
    public R<?> sendTopic() {
        for (int i = 0; i < 10; i++) {
            topicSender.send(i, "发他10个topic消息"+i);
        }
        return R.success("send.topic.ok");
    }

    @PostMapping("send/order")
    public R<?> generateOrder(@RequestBody OrderParam orderParam) {
        return portalOrderService.generateOrder(orderParam);
    }

}
