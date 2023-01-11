package org.cfc.redm.framework.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cfc.redm.commons.result.Result;
import org.cfc.redm.commons.result.ResultCode;
import org.cfc.redm.framework.security.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GlobalWebSecurityConfig {

    @Resource
    private TokenFilter tokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 注册tokenFilter
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                // 自定义认证鉴权失败处理器
                .exceptionHandling()
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
