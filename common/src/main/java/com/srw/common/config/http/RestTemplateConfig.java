package com.srw.common.config.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Description: RestTemplate配置
 * @Author: songrenwei
 * @Date: 2021/8/3 20:13
 */
@Slf4j
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(@Qualifier("requestFactory") OkHttp3ClientHttpRequestFactory requestFactory, @Lazy HttpClientInterceptor httpClientInterceptor) {
        return buildRestTemplate(requestFactory, httpClientInterceptor);
    }

    @Bean
    public RestTemplate restTemplate30(@Qualifier("requestFactory30") OkHttp3ClientHttpRequestFactory requestFactory, @Lazy HttpClientInterceptor httpClientInterceptor) {
        return buildRestTemplate(requestFactory, httpClientInterceptor);
    }

    @Bean
    public RestTemplate restTemplate60(@Qualifier("requestFactory60") OkHttp3ClientHttpRequestFactory requestFactory, @Lazy HttpClientInterceptor httpClientInterceptor) {
        return buildRestTemplate(requestFactory, httpClientInterceptor);
    }

    @Bean
    public RestTemplate restTemplate100(@Qualifier("requestFactory100") OkHttp3ClientHttpRequestFactory requestFactory, @Lazy HttpClientInterceptor httpClientInterceptor) {
        return buildRestTemplate(requestFactory, httpClientInterceptor);
    }

    @Lazy
    @Bean
    public HttpClientInterceptor httpClientLogInterceptor() {
        return new HttpClientInterceptor();
    }

    private RestTemplate buildRestTemplate(OkHttp3ClientHttpRequestFactory requestFactory, HttpClientInterceptor httpClientInterceptor) {
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        // 拦截器
        restTemplate.getInterceptors().add(httpClientInterceptor);
        // 转换器(修改默认字符集)
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        messageConverters.remove(1);
        StringHttpMessageConverter messageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        messageConverter.setWriteAcceptCharset(false);
        messageConverters.add(1, messageConverter);
        return restTemplate;
    }

}
