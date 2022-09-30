package org.cfc.redm.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RedmGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedmGatewayApplication.class, args);
    }

}
