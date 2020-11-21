package com.lv.study.lilei.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("talk")
@RestController
public class TalkController {

    @RequestMapping(method = RequestMethod.GET,path = "hiLiLei")
    public String callLiLei(){
        ServletRequestAttributes servletRequestAttributes =(ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String uuid = request.getHeader("UUID");
        System.out.println("uuid = " + uuid);
        return "Hi HanMeiMei";
    }
}
