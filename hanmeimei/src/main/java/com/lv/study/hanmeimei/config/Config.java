package com.lv.study.hanmeimei.config;

import com.lv.study.hanmeimei.interceptor.RequestContextHystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.HystrixPlugins;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.PostConstruct;


public class Config {

    public void init() {
        HystrixPlugins.reset();
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new RequestContextHystrixConcurrencyStrategy());
    }
}
