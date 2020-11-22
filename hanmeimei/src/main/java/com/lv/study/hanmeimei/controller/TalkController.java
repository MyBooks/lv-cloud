package com.lv.study.hanmeimei.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("talk")
public class TalkController {
    static final Logger log = LoggerFactory.getLogger(TalkController.class);

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    @RequestMapping(method = RequestMethod.GET,path = "sayHello")
    public String sayHello(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String uuid = request.getHeader("UUID");
        log.info("UUID:{}",uuid);
        return "hello uuid:"+uuid;
    }

    @HystrixCommand(fallbackMethod = "error")
    @RequestMapping(method = RequestMethod.GET,path = "talkToLiLei")
    public String talkToLiLei(){
        String forObject = restTemplate.getForObject("http://LiLeiServer/talk/hiLiLei", String.class);
        log.info(forObject);
        return forObject;
    }

    public String error(){
        log.error("sayHello|talkToLiLei fail");
        return "sorry~~~";
    }
}
