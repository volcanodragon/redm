package org.cfc.redm.auth.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cfc.redm.commons.result.Result;
import org.cfc.redm.commons.result.ResultCode;
import org.cfc.redm.commons.utils.JacksonUtils;
import org.cfc.redm.framework.security.filter.TokenFilter;
import org.cfc.redm.framework.security.userdetails.CurrentUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private TokenFilter tokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 注册tokenFilter
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                // 自定义登录成功/失败处理器
                .formLogin().successHandler((request, response, authentication) -> {
                    String username = ((CurrentUser) authentication.getPrincipal()).getUsername();
                    String token = (String) redisTemplate.opsForHash().get(username, "token");
                    // 清除旧的用户信息
                    if (StringUtils.isNotBlank(token)) {
                        redisTemplate.delete(token);
                        redisTemplate.delete(username);
                    }
                    redisTemplate.opsForHash().put(username, "authentication", authentication);
                    String tokenString = username + System.currentTimeMillis();
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(7);
                    token = encoder.encode(tokenString);
                    redisTemplate.opsForValue().set(token, username);
                    redisTemplate.opsForHash().put(username, "token", token);
                    redisTemplate.expire(token, 30, TimeUnit.MINUTES);
                    redisTemplate.expire(username, 30, TimeUnit.MINUTES);
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    ObjectMapper objectMapper = JacksonUtils.getObjectMapper();
                    response.getWriter().println(objectMapper.writeValueAsString(new Result<>().success().setData(token)));
                }).failureHandler((request, response, exception) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    var objectMapper = new ObjectMapper();
                    response.getWriter().println(objectMapper.writeValueAsString(new Result<>().buildByEnum(ResultCode.LOGIN_FAIL)));
                })
                // 自定义登出处理器
                .and().logout().logoutSuccessHandler((request, response, authentication) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    var objectMapper = new ObjectMapper();
                    response.getWriter().println(objectMapper.writeValueAsString(new Result<>().success()));
                })
                // 自定义认证鉴权失败处理器
                .and().exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    var objectMapper = new ObjectMapper();
                    response.getWriter().println(objectMapper.writeValueAsString(new Result<>().buildByEnum(ResultCode.NO_LOGIN)));
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    var objectMapper = new ObjectMapper();
                    response.getWriter().println(objectMapper.writeValueAsString(new Result<>().buildByEnum(ResultCode.ACCESS_DENIED)));
                })
                // 功能禁用
                .and()
                .securityContext().disable()
                .sessionManagement().disable()
                .cors().disable()
                .csrf().disable();
        return http.build();
    }
}
