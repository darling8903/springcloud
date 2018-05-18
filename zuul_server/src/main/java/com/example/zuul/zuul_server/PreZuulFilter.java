package com.example.zuul.zuul_server;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.HttpServletRequest;

public class PreZuulFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(PreZuulFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        Object token = request.getParameter("accessToken");
        if(token == null){
            logger.info("accesstoken is null");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);

            return null;
        }
        String host = request.getRemoteHost();
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(host);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return null;

    }
}
