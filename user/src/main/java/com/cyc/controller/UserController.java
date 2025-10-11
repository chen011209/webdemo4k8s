package com.cyc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.cloud.context.config.annotation.RefreshScope;


@RefreshScope  // 开启配置动态刷新 配置修改后无需重启服务
@RestController
public class UserController {


    // 使用@Value获取配置
    @Value("${app.message:默认消息}")
    private String message;

    @RequestMapping("/config")
    public String nacosConfig(){
        return message;
    }

    @RequestMapping("/userInfo")
    public String userInfo(){
        return "userInfo";
    }
}
