package com.springboot.springcorepractice.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Primary
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FootballCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Daily practicing 15 min of football to goal improvement..!!";
    }
}
