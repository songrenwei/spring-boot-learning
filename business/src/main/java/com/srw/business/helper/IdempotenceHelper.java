package com.srw.business.helper;

import cn.hutool.core.util.StrUtil;
import com.srw.common.exception.IdempotenceException;
import com.srw.common.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 幂等校验
 * @Author: songrenwei
 * @Date: 2020/12/10/10:53
 */
@RequiredArgsConstructor
@Component
public class IdempotenceHelper {

    private final RedisUtils redisUtils;

    /**
     * 幂等校验
     *
     * @param key
     */
    public void checkIdempotence(String key, long expireTime, TimeUnit timeUnit) {
        String value = redisUtils.getObject(key);

        if (StrUtil.isNotBlank(value)) {
            throw new IdempotenceException("重复提交!");
        }

        redisUtils.setObject(key, UUID.randomUUID().toString(), expireTime, timeUnit);
    }

    /**
     * 以摘要为key的幂等校验
     *
     * @param reqJSON
     */
    public void checkIdempotenceWithDigest(String reqJSON, long expire, TimeUnit timeUnit) {
        String digestKey = ParamDigestHelper.delDuplicationMD5(reqJSON);
        this.checkIdempotence(digestKey, expire, timeUnit);
    }

    /**
     * 以摘要为key的幂等校验且排除特殊参数
     *
     * @param reqJSON
     * @param excludeKeys
     */
    public void checkIdempotenceWithDigest(String reqJSON, long expire, TimeUnit timeUnit, String... excludeKeys) {
        String digestKey = ParamDigestHelper.delDuplicationMD5(reqJSON, excludeKeys);
        this.checkIdempotence(digestKey, expire, timeUnit);
    }

}
