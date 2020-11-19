package com.lv.study.hanmeimei.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("talk")
public class TalkController {

    @HystrixCommand(fallbackMethod = "error")
    @RequestMapping(method = RequestMethod.GET,path = "sayHello")
    public String sayHello(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String uuid = request.getHeader("UUID");
        System.out.println("uuid:" + uuid);
        return "hello uuid:"+uuid;
    }

    public String error(){
        return "sorry~~~";
    }
}
