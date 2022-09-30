package org.cfc.redm.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RedmEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedmEurekaServerApplication.class, args);
    }

}
