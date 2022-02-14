package com.srw.token;

import com.srw.common.component.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/4/8 10:13
 */
@SpringBootTest
class TokenTest {

    @Autowired
    private TokenService tokenService;

    @Test
    void testLogin() {
        String accessToken = tokenService.createOrUpdateToken(11111L);
        System.out.println(accessToken);
    }
}
