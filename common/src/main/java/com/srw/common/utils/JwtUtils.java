package com.srw.common.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SignatureException;
import java.util.Date;

@Slf4j
public class JwtUtils {

    private static final String JWT_SECRET = "s1r2e3n4w5e6i";

    private static SecretKey generalKey() {
        byte[] encodedKey = Base64Utils.encode(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Key key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * @param compactJws
     * @return
     * @throws ExpiredJwtException
     * @throws SignatureException
     */
    public static Claims parseJWT(String compactJws) throws ExpiredJwtException, SignatureException {
        SecretKey key = generalKey();
        try {
            // compactJws为jwt字符串
            Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws);
            // 得到body后我们可以从body中获取我们需要的信息
            // 比如 获取主题,当然，这是我们在生成jwt字符串的时候就已经存进来的
            return parseClaimsJws.getBody();
        } catch (MalformedJwtException e) {
            log.error(compactJws + ",解析异常:" + e);
            // jwt 解析错误
            throw new SignatureException("解析错误");
        }
    }
}
