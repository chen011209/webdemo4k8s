package com.cyc.controller;

import com.cyc.feign.user.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.cloud.context.config.annotation.RefreshScope;


@RefreshScope  // 开启配置动态刷新 配置修改后无需重启服务
@RestController
public class OrderController {
    // 使用@Value获取配置
    @Value("${app.message:默认消息}")
    private String message;
    @RequestMapping("/config")
    public String nacosConfig(){
        System.out.println(message);
        return message;
    }



    @Autowired
    private UserFeignClient userFeignClient;  // 注入 Feign 客户端

    @RequestMapping("/feign")
    public String testFeign(){
        System.out.println(userFeignClient.getUserInfo());
        return userFeignClient.getUserInfo();
    }
}
