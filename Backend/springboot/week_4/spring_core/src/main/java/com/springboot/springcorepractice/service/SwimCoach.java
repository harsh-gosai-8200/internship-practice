package com.springboot.springcorepractice.service;

public class SwimCoach implements Coach {

    public SwimCoach(){
        System.out.println("In constructor: "+getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meter as a warm up";
    }
}
