package com.tensquare.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import util.JwtUtil;

/**
 * @ClassName ManagerApplication
 * @Description TODO
 * @Author Silence
 * @Date 2020/1/31 14:45
 * @Version 1.0
 **/
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class);
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
