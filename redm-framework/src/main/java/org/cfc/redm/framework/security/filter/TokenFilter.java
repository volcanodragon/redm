package org.cfc.redm.framework.security.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class TokenFilter implements Filter {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest  = (HttpServletRequest) servletRequest;
        String token;
        // 放行登录请求
        if (httpServletRequest.getRequestURI().equals("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        token = httpServletRequest.getHeader("token");
        if (StringUtils.isBlank(token)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String username = (String) redisTemplate.opsForValue().get(token);
        if (username == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
            Authentication authentication = (Authentication) redisTemplate.opsForHash().get(username, "authentication");
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
            redisTemplate.expire(username, 30, TimeUnit.MINUTES);
            redisTemplate.expire(token, 30, TimeUnit.MINUTES);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
