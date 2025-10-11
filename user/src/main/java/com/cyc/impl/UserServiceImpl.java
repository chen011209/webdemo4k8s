package com.cyc.impl;

import com.cyc.dubbo.user.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

/**
 * 用户服务实现类
 */
@DubboService(version = "1.0.0", timeout = 3000,protocol = "dubbo")
@Component
public class UserServiceImpl implements UserService {

    @Override
    public String getUserInfo(Integer userId) {
        // 实现业务逻辑
        return "dubbo:"+userId;
    }
}