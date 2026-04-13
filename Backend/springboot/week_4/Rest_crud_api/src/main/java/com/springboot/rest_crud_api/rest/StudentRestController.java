package com.springboot.rest_crud_api.rest;

import com.springboot.rest_crud_api.entity.Student;
import com.springboot.rest_crud_api.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private  List<Student> theStudent;

    //post construct to load the student data
    @PostConstruct
    public void loadData(){
        theStudent = new ArrayList<>();
        theStudent.add(new Student("Harsh", "Gosai"));
        theStudent.add(new Student("Jeet", "Maharaj"));
        theStudent.add(new Student("Rudra", "Gosai"));
        theStudent.add(new Student("Jeel", "Patel"));
        theStudent.add(new Student("keval", "Raj"));
    }

    /**
     * retrive all the students
     * @return
     */
    @GetMapping("/students")
    //endpoint for /student
    public List<Student> getStudent(){

        return theStudent;
    }

    /**
     * retrive a student by id
     * @param studentId
     * @return
     */
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        if((studentId>=theStudent.size()) || (studentId<0)){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return theStudent.get(studentId);
    }



}





