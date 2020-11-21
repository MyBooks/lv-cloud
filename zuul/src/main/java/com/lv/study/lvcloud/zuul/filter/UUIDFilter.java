package com.lv.study.lvcloud.zuul.filter;

import com.lv.study.lvcloud.zuul.utils.UUIDUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDFilter extends ZuulFilter {
    /**
     * 过滤器类型
     * pre：可以在请求被路由之前调用
     * route：在路由请求时候被调用
     * post：在route和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     *
     * @return String 路由类型
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器顺序
     *
     * @return int 顺序大小
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行
     *
     * @return boolean 是否执行
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器执行主体
     *
     * @return Object
     * @throws ZuulException Zuul异常
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext(); // 在zuul中可以用requestContext快速拿到上下文
        currentContext.addZuulRequestHeader("UUID", UUIDUtil.getUUID()); // 在请求中设置uuid
        return null;
    }
}
