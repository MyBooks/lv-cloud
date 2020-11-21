package com.lv.study.hanmeimei;


import com.lv.study.hanmeimei.interceptor.RestTemplateInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;


@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class HanMeiMeiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HanMeiMeiApplication.class,args);
    }

    @LoadBalanced
    @Bean
    RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if(interceptors != null){
            interceptors.add(new RestTemplateInterceptor());
        }else{
            restTemplate.setInterceptors(Collections.singletonList(new RestTemplateInterceptor()));
        }
        return restTemplate;
    }

}
