package com.srw.config;

import com.srw.intercept.TokenIntercept;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 全局配置
 * @Author: renwei.song
 * @Date: 2021/4/8 10:13
 */
@Configuration
public class WebMvcConfigurerConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 添加请求映射，就是对哪些地址进行跨域处理
                .allowedOrigins("*") // 特定来源的允许来源列表。这里配置*表示所以网站都可进行跨域，这里生产介意指定特定的地址
                .allowedHeaders("*") // 允许请求头携带的标题： "Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers"
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH") // 允许跨域的请求方式，可以进行指定:GET,POST
                .allowCredentials(true) // 浏览器是否携带凭证
                .maxAge(3600); // 客户端缓存的时间，默认为1800（30分钟）
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**");
        registry.addResourceHandler("/")
                .addResourceLocations("/**");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
         registry.addInterceptor(new TokenIntercept())
                 .addPathPatterns("/**") // 拦截所有
                 // 不拦截路径
                 .excludePathPatterns("/user/**")
                 .excludePathPatterns("/metrics/**")
                 .excludePathPatterns("/test/**")
                 .excludePathPatterns("/admin/**")
                 .excludePathPatterns("/aliyun/**");
    }

}