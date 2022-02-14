package com.srw.common.utils;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @Description: redis操作工具类
 * @Author: songrenwei
 * @Date: 2020/12/14/15:47
 */
@SuppressWarnings("unchecked")
public class RedisUtils {

    /**
     * redis超时时间
     */
    public static final Long SECOND_FIVE = 5L;

    public static final Long SECOND_TEN = 15L;
    public static final Long HOUR_24 = 24L;

    public static final Long MINUTES_FIVE = 5L;
    public static final Long MINUTES_TEN = 10L;

    private RedisTemplate<String, Object> redisTemplate;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置对象
     *
     * @param key
     * @param clazz
     * @param <T>
     */
    public <T> void setObject(String key, T clazz) {
        redisTemplate.opsForValue().set(key, clazz);
    }

    /**
     * 设置对象,含有效期,单位为秒
     *
     * @param key
     * @param clazz
     * @param timeout
     * @param <T>
     */
    public <T> void setObject(String key, T clazz, long timeout) {
        redisTemplate.opsForValue().set(key, clazz, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置对象,含有效期,单位可以自定义
     *
     * @param key
     * @param clazz
     * @param timeout
     * @param unit
     * @param <T>
     */
    public <T> void setObject(String key, T clazz, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, clazz, timeout, unit);
    }

    /**
     * 获取value
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getObject(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void del(String key) {
        if (getObject(key) != null) {
            redisTemplate.delete(key);
        }
    }

    /**
     * redis加锁
     *
     * @param key
     * @param value
     * @param timeOut
     * @param timeUnit
     * @return
     */
    public Boolean setKey(String key, String value, Long timeOut, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeOut, timeUnit);
    }

    /**
     * key是否存在
     *
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

}
