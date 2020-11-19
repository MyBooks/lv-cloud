package com.lv.study.hanmeimei.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;


@RestController
@RequestMapping("talk")
public class TalkController {

    @HystrixCommand(fallbackMethod = "error")
    @RequestMapping(method = RequestMethod.GET,path = "sayHello")
    public String sayHello(){
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        System.out.println("requestAttributes = " + requestAttributes);
        return "hello";
    }

    public String error(){
        return "sorry~~~";
    }
}
