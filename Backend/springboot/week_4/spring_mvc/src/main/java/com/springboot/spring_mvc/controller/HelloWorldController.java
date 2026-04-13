package com.springboot.spring_mvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

//    @RequestMapping("/showForm")
//    public String showForm(){
//        return "helloworld-form";
//    }

    @GetMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld-process";
    }

//    @RequestMapping("/processFormByController")
//    public String letsShout(HttpServletRequest request, Model model){
//
//        String firstName = request.getParameter("studentFirstName");
//        firstName = firstName.toUpperCase();
//        String lastName = request.getParameter("studentLastName");
//        lastName = lastName.toUpperCase();
//
//        String result = "Hey! "+firstName+" "+lastName;
//        model.addAttribute("message", result);
//        return "helloworld-process";
//    }

    @RequestMapping("/processFormByController")
    public String letsShout(@RequestParam("studentFirstName") String firstName,@RequestParam("studentLastName") String lastName, Model model ){
        firstName = firstName.toUpperCase();
        lastName = lastName.toUpperCase();

        String result = "Hey! "+firstName+" "+lastName;
        model.addAttribute("message", result);
        return "helloworld-process";
    }
}
