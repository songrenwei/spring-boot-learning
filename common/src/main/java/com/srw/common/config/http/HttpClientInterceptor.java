package com.srw.common.config.http;

import io.micrometer.core.instrument.binder.okhttp3.OkHttpMetricsEventListener;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @Description: RestTemplate请求拦截器（延迟加载）
 * @Author: songrenwei
 * @Date: 2021/8/3 20:13
 */
@Slf4j
public class HttpClientInterceptor implements ClientHttpRequestInterceptor {

    @NotNull
    @Override
    public ClientHttpResponse intercept(@NotNull HttpRequest request, @NotNull byte[] body, ClientHttpRequestExecution execution) throws IOException {
        log.info("URI_PATTERN: {}", request.getURI().getPath());
        request.getHeaders().add(OkHttpMetricsEventListener.URI_PATTERN, request.getURI().getPath());
        return execution.execute(request, body);
    }

}
