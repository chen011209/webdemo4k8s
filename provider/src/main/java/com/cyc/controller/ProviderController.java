package com.cyc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProviderController {

    @RequestMapping("/userInfo")
    public String userInfo(){
        return "userIn2";
    }
}
