package org.cfc.redm.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cfc.redm.commons.result.Result;
import org.cfc.redm.commons.result.ResultCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin().successHandler((request, response, authentication) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    var objectMapper = new ObjectMapper();
                    response.getWriter().println(objectMapper.writeValueAsString(new Result<>().success()));
                }).failureHandler((request, response, exception) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    var objectMapper = new ObjectMapper();
                    response.getWriter().println(objectMapper.writeValueAsString(new Result<>().buildByEnum(ResultCode.LOGIN_FAIL)));
                })
                .and().logout().logoutSuccessHandler((request, response, authentication) -> {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json");
                    var objectMapper = new ObjectMapper();
                    response.getWriter().println(objectMapper.writeValueAsString(new Result<>().success()));
                })
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
                .and().cors().disable()
                .csrf().disable();
        return http.build();
    }

}
