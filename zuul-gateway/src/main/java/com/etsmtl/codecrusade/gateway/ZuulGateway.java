package com.etsmtl.codecrusade.gateway;

import com.etsmtl.codecrusade.gateway.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@Import(SecurityConfig.class)
public class ZuulGateway {
    public static void main(String[] args) {
        SpringApplication.run(ZuulGateway.class, args);
    }
}