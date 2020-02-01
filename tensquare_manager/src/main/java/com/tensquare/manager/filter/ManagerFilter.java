package com.tensquare.manager.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ManagerFilter
 * @Description TODO 后台管理员用户网关，统一鉴权
 * @Author Silence
 * @Date 2020/1/31 18:04
 * @Version 1.0
 **/
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;
    /**
     * 在请求前pre或者后post执行
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 有多个过滤器时的执行顺序，数字越小越先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前过滤器是否开启，true 开启
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器内的执行逻辑，返回任何Object的值都表示继续执行
     * setSendZuulResponse(false) 停止执行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("经过zuul过滤器！");
        RequestContext context= RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        //特殊请求放行
        if(request.getMethod().equals("OPTIONS")){
            return null;
        }
        //登陆请求放行
        if(request.getRequestURI().indexOf("login")>0){
            return null;
        }

        String header = request.getHeader("Authorization");
        if(StringUtils.isNotEmpty(header)&&header.startsWith("Bearer ")){
            String token = header.substring(7);
            try {
                Claims claims = jwtUtil.parseJWT(token);
                String roles = (String) claims.get("roles");
                if("admin".equals(roles)){
                    //把头信息转发下去，并且放行
                    context.addZuulRequestHeader("Authorization",header);
                    return null;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        context.setSendZuulResponse(false);//终止运行
        context.setResponseStatusCode(403);
        Result result = new Result(false, StatusCode.ACCESSERROR, "权限不足！");
        String json= JSON.toJSONString(result);
        context.setResponseBody(json);
        context.getResponse().setContentType("application/json;charset=utf-8");
        return null;
    }
}
