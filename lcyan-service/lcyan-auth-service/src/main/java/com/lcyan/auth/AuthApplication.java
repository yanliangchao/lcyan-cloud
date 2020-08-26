package com.lcyan.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication(scanBasePackages = {"com.lcyan"})
@ComponentScan("com.lcyan")
@EnableCaching
@EnableDiscoveryClient
@EnableAuthorizationServer
@EnableJpaRepositories(basePackages = { "com.lcyan.basic.core.persistence.repository", "com.lcyan.auth.jpa" })
@EntityScan(basePackages = { "com.lcyan.basic.core.persistence.entity", "com.lcyan.auth.api.model" })
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
