package com.srw.schedule.service.impl;

import com.srw.schedule.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        // 邮件主题
        message.setSubject("来自帅老公的晚安心语");
        // 发送者
        message.setFrom(from);
        // 接收者575506974@qq.com
        message.setTo("575506974@qq.com");
        message.setSentDate(new Date());
        message.setText("宝子  我想你，我要亲你，吻你，抱你，一生一世。\n" +
                "今天我爱你，比昨天多，但没有明天多，每天亦是如此。\n" +
                " \n" +
                "能够遇见你，对我来说是一生的幸运。\n" +
                "你是我的世界，我的世界里只有你一个人。\n" +
                "我希望可以和你一起体验清爽的风，清晨的阳光，甚至是落日的余晖，这一切刚刚好。\n" +
                " \n" +
                "晚安，我的梦里全是你!");
        javaMailSender.send(message);
    }

}
