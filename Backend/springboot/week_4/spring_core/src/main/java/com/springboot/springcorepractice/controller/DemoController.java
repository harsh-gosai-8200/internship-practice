package com.springboot.springcorepractice.controller;

import com.springboot.springcorepractice.service.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Coach myCoach;
    private Coach anotherCoach;
    private Coach swimCoach;

//    /**
//     * fild injection for coach
//     * it's not recommended
//     */
//    @Autowired
//    private Coach MyCoach;

//    /**
//     * constructor injection for coach
//     * @param theCoach
//     */
//    @Autowired
//    public DemoController(Coach theCoach){
//        myCoach = theCoach;
//    }

//    /**
//     * Setter injection for coach with Qualifier
//     *  checking bean prototype scope
//     * @param footballCoach
//     */
//    @Autowired
//    public void setMyCoach(@Qualifier("footballCoach") Coach footballCoach,
//                           @Qualifier("footballCoach") Coach anotherFootballCoach){
//        myCoach = footballCoach;
//        anotherCoach = anotherFootballCoach;
//    }

    @Autowired
    public DemoController(@Qualifier("swimCoach") Coach swimCoach){
        System.out.println("In constructor : "+getClass().getSimpleName());
        this.swimCoach = swimCoach;
    }

    /**
     * Setter injection for coach with Qualifier
     * checking bean singleton scope
     * @param cricketCoach
     */
    @Autowired
    public void setMyCoach(@Qualifier("cricketCoach") Coach cricketCoach,
                           @Qualifier("cricketCoach") Coach anotherCricketCoach){
        myCoach = cricketCoach;
        anotherCoach = anotherCricketCoach;
    }

//    /**
//     * Setter injection for coach with @Primary
//     * @param cricketCoach
//     */
//    @Autowired
//    public void setMyCoach(Coach cricketCoach){
//        myCoach = cricketCoach;
//    }


    /**
     * geting request from browser and send responce from service
     * @return
     */
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }

    /**
     * checking the bean scope
     * @return
     */
    @GetMapping("/check")
    public String check(){
        return "Comparing beans : myCoach == anotherCoach =>"+(myCoach==anotherCoach);
    }

    /**
     * geting request from browser and send responce from service
     * @return
     */
    @GetMapping("/dailyswimworkout")
    public String getDailySwimWorkout(){
        return swimCoach.getDailyWorkout();
    }
}

