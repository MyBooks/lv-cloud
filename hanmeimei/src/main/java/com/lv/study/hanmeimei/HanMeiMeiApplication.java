package com.lv.study.hanmeimei;

import com.lv.study.hanmeimei.interceptor.RequestContextHystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.HystrixPlugins;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class HanMeiMeiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanMeiMeiApplication.class,args);
    }

}
