package com.lv.study.hanmeimei.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.support.RequestContext;

@RestController
@RequestMapping("talk")
public class TalkController {

    @HystrixCommand()
    @RequestMapping(method = RequestMethod.GET,path = "sayHello")
    public String sayHello(){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        System.out.println("requestAttributes = " + requestAttributes);
        return "hello";
    }
}
