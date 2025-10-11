package com.cyc.feign.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user")
public interface UserFeignClient {
    @GetMapping("/userInfo")
    String getUserInfo();
}
