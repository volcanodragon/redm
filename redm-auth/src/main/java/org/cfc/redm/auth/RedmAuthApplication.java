package org.cfc.redm.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan
@EnableDiscoveryClient
@SpringBootApplication
public class RedmAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedmAuthApplication.class, args);
    }

}
