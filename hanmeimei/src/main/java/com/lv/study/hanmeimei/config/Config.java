package com.lv.study.hanmeimei.config;

import com.lv.study.hanmeimei.interceptor.RequestContextHystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import com.netflix.hystrix.strategy.properties.HystrixProperty;

import javax.annotation.PostConstruct;


public class Config {

    @PostConstruct
    public void init() {
        // 现获取hystrix的所有插件
        HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance().getEventNotifier();
        HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance().getMetricsPublisher();
        HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance().getPropertiesStrategy();
        HystrixCommandExecutionHook commandExecutionHook = HystrixPlugins.getInstance().getCommandExecutionHook();
        // 重置hystrix的插件
        HystrixPlugins.reset();
        // 重新设置hystrix的插件
        HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
        HystrixPlugins.getInstance().registerCommandExecutionHook(commandExecutionHook);
        HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
        HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new RequestContextHystrixConcurrencyStrategy()); // 注册自己的并发策略
    }
}
