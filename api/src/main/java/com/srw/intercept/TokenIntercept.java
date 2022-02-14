package com.srw.intercept;

import com.srw.common.bean.Session;
import com.srw.common.component.TokenService;
import com.srw.common.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Description: token拦截器
 * @Author: renwei.song
 * @Date: 2021/4/8 10:13
 */
@Slf4j
public class TokenIntercept implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        TokenService appTokenService = SpringContextUtils.getBean("tokenService");
        String token = request.getHeader("accessToken");
        token = Objects.nonNull(token)?token:request.getParameter("accessToken");
        // token不存在
        if (StringUtils.isBlank(token)) {
            log.error("token为空");
            return false;
        }
        // token解析
        Session session = appTokenService.decodeToken(token);
        Session s = (Session) request.getSession().getAttribute("session");
        // 将id放在session中共享
        if (null == s || !(s.getUserId().equals(session.getUserId()))) {
            request.getSession().setAttribute("session", session);
        }
        return true;
    }
}