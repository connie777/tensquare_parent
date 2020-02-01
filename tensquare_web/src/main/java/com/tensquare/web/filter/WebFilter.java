package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ManagerFilter
 * @Description TODO 普通用户网关，需要转发请求头信息
 * @Author Silence
 * @Date 2020/1/31 18:04
 * @Version 1.0
 **/
@Component
public class WebFilter extends ZuulFilter {
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
     * setSendZuulResponse("false") 停止执行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("经过zuul过滤器！");
        RequestContext context= RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String header = request.getHeader("Authorization");
        if(StringUtils.isNotEmpty(header)){
            //传递头信息
            context.addZuulRequestHeader("Authorization", header);
        }
        return null;
    }
}
