package com.springboot.rest_crud_api.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoRestController {

    //code for /hello
    @GetMapping("/hello")
    public String sayHello(){
        return "hello there!";
    }
}
