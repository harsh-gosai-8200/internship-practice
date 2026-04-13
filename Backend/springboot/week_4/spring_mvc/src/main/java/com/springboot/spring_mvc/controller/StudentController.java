package com.springboot.spring_mvc.controller;

import com.springboot.spring_mvc.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Value("${country}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @Value("${currentDevices}")
    private List<String> currentDevices;

    @GetMapping("showStudentForm")
    public String showForm(Model model){
        //create student obj
        Student student =new Student();
        //add student obj to model
        model.addAttribute("student", student);
        //add list of country to model
        model.addAttribute("countries", countries);
        //add list of languages to model
        model.addAttribute("languages", languages);
        //add list of current devices to model
        model.addAttribute("currentDevices", currentDevices);
        return "student-form";
     }

    @PostMapping("/processStudentForm")
    public String processStudent(@ModelAttribute("student") Student student){

        //System.out.println("Student Data : " + student.getFirstName() +" " + student.getLastName() + " " + student.getEmail());

        return "student-confirmation";
    }
}
