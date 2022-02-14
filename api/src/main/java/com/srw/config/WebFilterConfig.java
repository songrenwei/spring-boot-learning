package com.srw.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.util.Base64Utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Description: 全局过滤器
 * @Author: renwei.song
 * @Date: 2021/4/8 10:13
 */
@Slf4j
@Configuration
public class WebFilterConfig {

    @Bean
    public FilterRegistrationBean<AppWebFilter> refererFilterRegistration() {
        FilterRegistrationBean<AppWebFilter> registration = new FilterRegistrationBean<>();
        //注入过滤器
        registration.setFilter(new AppWebFilter());
        //过滤规则
        registration.addUrlPatterns("/*");
        //过滤器名称
        registration.setName("app");
        //过滤器顺序
        registration.setOrder(1);

        return registration;
    }

    class AppWebFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) {

        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            request.setCharacterEncoding("utf-8");
            // 打印请求头部信息
//            logAppRequestHeader(convert(request));
            if (HttpMethod.OPTIONS.matches(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                filterChain.doFilter(request, response);
            }
        }

        private AppRequestHeader convert(HttpServletRequest request) {
            AppRequestHeader appRequestHeader = new AppRequestHeader();
            appRequestHeader.setAccessToken(request.getHeader("accessToken"));
            appRequestHeader.setNewAccessToken(request.getHeader("newAccessToken"));

            String context = request.getHeader("context");
            if (StringUtils.isNotEmpty(context)) {
                try {
                    appRequestHeader.setContext(new String(Base64Utils.decodeFromString(context), StandardCharsets.UTF_8));
                } catch (Exception ex) {
                    //如果有异常，则保存原始数据
                    appRequestHeader.setContext(context);
                }
            }
            appRequestHeader.setTimestamp(request.getHeader("timestamp"));
            appRequestHeader.setRequestIp(getIpAddress(request));
            appRequestHeader.setTraceId(MDC.get("X-B3-TraceId"));
            return appRequestHeader;
        }

        private void logAppRequestHeader(AppRequestHeader appRequestHeader) {
            log.info(appRequestHeader.toString());
        }

        @Override
        public void destroy() {

        }

        /**
         * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
         *
         * @param request
         * @return
         */
        final String getIpAddress(HttpServletRequest request) {
            String unknown = "unknown";

            // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
            String ip = request.getHeader("X-Forwarded-For");

            if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                    ip = request.getHeader("Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                    ip = request.getHeader("WL-Proxy-Client-IP");
                }
                if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_CLIENT_IP");
                }
                if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                }
                if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
                    ip = request.getRemoteAddr();
                }
            } else if (ip.length() > 15) {
                String[] ips = ip.split(",");
                for (String strIp : ips) {
                    if (!(unknown.equalsIgnoreCase(strIp))) {
                        ip = strIp;
                        break;
                    }
                }
            }
            return ip;
        }
    }
}
