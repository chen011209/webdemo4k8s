package com.cyc.controller;

import com.cyc.dubbo.user.UserService;
import com.cyc.feign.user.UserFeignClient;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.cloud.context.config.annotation.RefreshScope;


@RefreshScope  // 开启配置动态刷新 配置修改后无需重启服务(读取nacos配置使用)
@RestController
public class ConsumerController {
    /**
     * 读取nacos配置
     */
    @Value("${app.message:默认消息}")
    private String message;
    @RequestMapping("/config")
    public String nacosConfig(){
        System.out.println(message);
        return message;
    }

    /**
     * 使用feign实现服务间调用
     */
    @Autowired
    private UserFeignClient userFeignClient;  // 注入 Feign 客户端
    @RequestMapping("/feign")
    public String testFeign(){
        System.out.println(userFeignClient.getUserInfo());
        return userFeignClient.getUserInfo();
    }

    /**
     * 使用Dubbo(RPC)实现服务间调用
     * @return
     */
    // Dubbo 方式调用
    // 引用远程服务，version需与服务提供者一致
    @DubboReference(
            version = "1.0.0",
            check = false,  // 启动时不检查服务是否存在
            timeout = 3000,
            retries = 0,
            protocol = "dubbo"
    )
    private UserService userService;
    @RequestMapping("/dubbo")
    public String testDubbo(){
        System.out.println(userService.getUserInfo(1));
        return userService.getUserInfo(1);
    }
}
