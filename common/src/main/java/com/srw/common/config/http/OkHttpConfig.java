package com.srw.common.config.http;

import com.srw.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description: OkHttp配置
 * @Author: songrenwei
 * @Date: 2021/8/3 16:13
 */
@Slf4j
@Configuration
public class OkHttpConfig {

    /**
     * OkHttp连接池
     */
    @Bean
    public ConnectionPool connectionPool() {
        int maxIdleConnections = 50; // 连接池空闲时连接的最大数量
        int keepAliveDuration = 300; // 连接空闲时间最多为300秒
        return new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.SECONDS);
    }

    @Bean("okHttpClient")
    public OkHttpClient okHttpClient(ConnectionPool connectionPool) {
        return new OkHttpClient().newBuilder().connectionPool(connectionPool).connectTimeout(5, TimeUnit.SECONDS).readTimeout(Constants.TEN_SECONDS, TimeUnit.SECONDS).build();
    }

    @Bean("okHttpClient30")
    public OkHttpClient okHttpClient30(ConnectionPool connectionPool) {
        return new OkHttpClient().newBuilder().connectionPool(connectionPool).connectTimeout(5, TimeUnit.SECONDS).readTimeout(Constants.THIRTY_SECONDS, TimeUnit.SECONDS).build();
    }

    @Bean("okHttpClient60")
    public OkHttpClient okHttpClient60(ConnectionPool connectionPool) {
        return new OkHttpClient().newBuilder().connectionPool(connectionPool).connectTimeout(5, TimeUnit.SECONDS).readTimeout(Constants.SIXTY_SECONDS, TimeUnit.SECONDS).build();
    }

    @Bean("okHttpClient100")
    public OkHttpClient okHttpClient100(ConnectionPool connectionPool) {
        return new OkHttpClient().newBuilder().connectionPool(connectionPool).connectTimeout(5, TimeUnit.SECONDS).readTimeout(Constants.ONE_HUNDRED_SECONDS, TimeUnit.SECONDS).build();
    }

    @Bean("requestFactory")
    public OkHttp3ClientHttpRequestFactory requestFactory(@Qualifier("okHttpClient") OkHttpClient okHttpClient) {
        addShutdownHook(okHttpClient);
        return new OkHttp3ClientHttpRequestFactory(okHttpClient);
    }

    @Bean("requestFactory30")
    public OkHttp3ClientHttpRequestFactory requestFactory30(@Qualifier("okHttpClient30") OkHttpClient okHttpClient) {
        addShutdownHook(okHttpClient);
        return new OkHttp3ClientHttpRequestFactory(okHttpClient);
    }

    @Bean("requestFactory60")
    public OkHttp3ClientHttpRequestFactory requestFactory60(@Qualifier("okHttpClient60") OkHttpClient okHttpClient) {
        addShutdownHook(okHttpClient);
        return new OkHttp3ClientHttpRequestFactory(okHttpClient);
    }

    @Bean("requestFactory100")
    public OkHttp3ClientHttpRequestFactory requestFactory100(@Qualifier("okHttpClient100") OkHttpClient okHttpClient) {
        addShutdownHook(okHttpClient);
        return new OkHttp3ClientHttpRequestFactory(okHttpClient);
    }

    private void addShutdownHook(OkHttpClient okHttpClient) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            ConnectionPool connectionPool = okHttpClient.connectionPool();
            connectionPool.evictAll();
            log.info("OkHttp connections idle: {}, all: {}", connectionPool.idleConnectionCount(), connectionPool.connectionCount());
            ExecutorService executorService = okHttpClient.dispatcher().executorService();
            executorService.shutdown();
            try {
                executorService.awaitTermination(3, TimeUnit.MINUTES);
                log.info("OkHttp ExecutorService closed.");
            } catch (InterruptedException e) {
                log.warn("InterruptedException on destroy()", e);
            }
        }, "OkHttpShutDownHook"));
    }

}
