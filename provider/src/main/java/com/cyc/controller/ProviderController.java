package com.cyc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.context.config.annotation.RefreshScope;


@RefreshScope  // 开启配置动态刷新 配置修改后无需重启服务
@RestController
public class ProviderController {

    @RequestMapping("/userInfo")
    public String userInfo(){
        return "userIn2";
    }
}
