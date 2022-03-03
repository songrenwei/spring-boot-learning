package com.srw.common.component;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.srw.common.bean.Session;
import com.srw.common.exception.BusinessException;
import com.srw.common.utils.JwtUtils;
import com.srw.common.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SignatureException;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/4/7 16:33
 */
@Service
public class TokenService {

    private RedisUtils redisUtils;

    /**
     * 失效时间
     */
    private static final long TOKEN_TIME_OUT = 60 * 60 * 1000;

    private static final String PREFIX = "TOKEN_";

    @Autowired
    public TokenService(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    public String createOrUpdateToken(Long userId) {
        String key = StringUtils.join(PREFIX, userId);
        String cacheToken = redisUtils.getObject(key);
        if (StringUtils.isNotBlank(cacheToken)) {
            redisUtils.del(key);
        }
        String token = createToken(userId);
        redisUtils.setObject(key, token, TOKEN_TIME_OUT, TimeUnit.MILLISECONDS);
        return token;
    }

    public String createToken(Long userId) {
        Session session = new Session(userId);
        return JwtUtils.createJWT("appToken", JSONUtil.toJsonStr(session), TOKEN_TIME_OUT);
    }

    /**
     * 解密token
     *
     * @param token
     * @return
     */
    public Session decodeToken(String token) {
        try {
            Claims claims = JwtUtils.parseJWT(token);
            String body = claims.getSubject();
            Session session = JSONUtil.toBean(body, new TypeReference<Session>() {
            },false);
            if (session != null) {
                String key = StringUtils.join(PREFIX, session.getUserId());
                String cacheToken = redisUtils.getObject(key);
                if (StringUtils.isBlank(cacheToken)) {
                    throw new BusinessException("登录过期，请重新登陆！");
                }
                if (!token.equalsIgnoreCase(cacheToken)) {
                    throw new BusinessException("存在异地登录，请重新登录！");
                }
                // 自动续期
                this.createOrUpdateToken(session.getUserId());
            }
            return session;
        } catch (ExpiredJwtException | SignatureException ex) {
            throw new BusinessException("token无效，请重新获取");
        }
    }

}
