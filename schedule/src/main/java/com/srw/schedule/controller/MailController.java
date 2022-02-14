package com.srw.schedule.controller;

import com.srw.schedule.service.MailService;
import lombok.RequiredArgsConstructor;
import net.dreamlu.mica.core.result.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @RequestMapping("/sendMail")
    public R<?> sendMail() {
        mailService.sendMail();
        return R.success();
    }

}
