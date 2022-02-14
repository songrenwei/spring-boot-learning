package com.srw.controller;

import com.srw.mq.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: songrenwei
 * @Date: 2020/11/10/13:56
 */
@RequiredArgsConstructor
@Slf4j
@RestController
public class KafkaController {
    private final KafkaProducer kafkaProducer;

    @GetMapping("/send")
    public String sendKafkaMsg() {
        kafkaProducer.send("this is kafka msg!!!");
        return "ok";
    }
}
