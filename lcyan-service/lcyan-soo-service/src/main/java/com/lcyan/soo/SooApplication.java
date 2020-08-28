package com.lcyan.soo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.lcyan"})
@ComponentScan("com.lcyan")
@EnableCaching
@EnableDiscoveryClient
//@EnableAuthorizationServer
//@EnableJpaRepositories(basePackages = { "com.lcyan.basic.core.persistence.repository", "com.lcyan.auth.jpa" })
//@EntityScan(basePackages = { "com.lcyan.basic.core.persistence.entity", "com.lcyan.auth.api.model" })
public class SooApplication {

    public static void main(String[] args) {
        SpringApplication.run(SooApplication.class, args);
    }

}
