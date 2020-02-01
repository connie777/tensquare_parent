package com.tensquare.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @ClassName WebApplication
 * @Description TODO
 * @Author Silence
 * @Date 2020/1/31 15:04
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }
}
