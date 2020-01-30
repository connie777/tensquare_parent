package com.tensquare.user.interceptor;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName JwtInterceptor
 * @Description TODO
 * @Author Silence
 * @Date 2020/1/30 13:26
 * @Version 1.0
 **/
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器");
        //无论如何都放行。具体能不能操作还是在具体的操作中取判断
        //拦截器只是负责把请求头中包含token的令牌进行一个解析验证
        String header = request.getHeader("Authorization");
        if(StringUtils.isNotEmpty(header)&&header.startsWith("Bearer ")){
            String token=header.substring(7);
            try{
                Claims claims = jwtUtil.parseJWT(token);
                String role= (String) claims.get("roles");
                if(StringUtils.isNotEmpty(role)){
                    if("admin".equals(role)){
                        request.setAttribute("admin_claims",claims);
                    }
                    if("user".equals(role)){
                        request.setAttribute("user_claims",claims);
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("权限不足！！！");
            }
        }
        return true;
    }
}
