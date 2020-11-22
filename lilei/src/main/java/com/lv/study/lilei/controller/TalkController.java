package com.lv.study.lilei.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("talk")
@RestController
public class TalkController {
    static final Logger logger = LoggerFactory.getLogger(TalkController.class);

    @RequestMapping(method = RequestMethod.GET,path = "hiLiLei")
    public String callLiLei(){
        ServletRequestAttributes servletRequestAttributes =(ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String uuid = request.getHeader("UUID");
        logger.info("UUID:{}",uuid);
        return "Hi HanMeiMei";
    }
}
