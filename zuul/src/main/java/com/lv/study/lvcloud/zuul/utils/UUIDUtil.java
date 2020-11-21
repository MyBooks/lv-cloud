package com.lv.study.lvcloud.zuul.utils;

import java.util.UUID;

public class UUIDUtil {
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    static public String getUUID(){
        String uuid = threadLocal.get();
        if(uuid == null){
            uuid = UUID.randomUUID().toString().replace("-","");
            threadLocal.set(uuid);
        }
        return uuid;
    }
}
