package com.springboot.springcorepractice.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CricketCoach implements Coach{

    /**
     * method that return string of daily workout
     * @return
     */
    @Override
    public String getDailyWorkout() {
        return "Practicing fast bowling for 15 minutes in duration of 5 min..";
    }

//    /**
//     * demonstrate postConstruct
//     */
//    @PostConstruct
//    public void onStartup(){
//        System.out.println("In onStartup() :" + getClass().getSimpleName());
//    }

//    /**
//     * demonstrate preDestroy
//     */
//    @PreDestroy
//    public void onCleanup(){
//        System.out.println("In onCleanup() :" + getClass().getSimpleName());
//    }
}
