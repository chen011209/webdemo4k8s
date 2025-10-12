package com.cyc.feign.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "provider")
public interface UserFeignClient {
    @GetMapping("/userInfo")
    String getUserInfo();
}
