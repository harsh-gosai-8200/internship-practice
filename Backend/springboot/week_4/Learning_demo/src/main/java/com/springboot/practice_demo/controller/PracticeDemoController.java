package com.springboot.practice_demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PracticeDemoController {

    @Value("${user.name}")
    private String userName;

    @Value("${user.role}")
    private String userRole;

    @GetMapping("/")
    public String sayHello(){
        return "hello !!";
    }

    @GetMapping("/gym")
    public String gymSlogan(){
        return "you can do it buddy !!";
    }

    @GetMapping("/line")
    public String lineOfTheDay(){
        return "real eyes realise real-lies !!";
    }

    @GetMapping("/userinfo")
    public String getUserInfo(){
        return "User:"+userName+" role:"+userRole;
    }
}
