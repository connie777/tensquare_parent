package com.tensquare.qa.config;

import com.tensquare.qa.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @ClassName InterceptorConfig
 * @Description TODO
 * @Author Silence
 * @Date 2020/1/30 13:31
 * @Version 1.0
 **/
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    JwtInterceptor interceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器要声明拦截器对象和拦截的路径
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login/**");
    }
}
