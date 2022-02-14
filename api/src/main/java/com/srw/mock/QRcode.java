package com.srw.mock;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 二维码
 * @Author: renwei.song
 * @Date: 2020/12/29 10:21
 */
@Slf4j
@Component
public class QRcode {

    public void generateQRcode(HttpServletResponse response) {
        log.info("==========打包测试==========");
//        QrConfig config = new QrConfig(300, 300);
//        try {
//            QrCodeUtil.generate("嘿嘿，被你发现了", config, "jpg", response.getOutputStream());
//        } catch (IOException e) {
//            log.info("QRcode generateQRcode Exception: ", e);
//        }
    }

    public static void main(String[] args) {
        QrConfig config = new QrConfig(300, 300);
        QrCodeUtil.generate("http://www.baidu.com", config, FileUtil.file("E:\\qrcode\\1.jpg"));
    }

}
