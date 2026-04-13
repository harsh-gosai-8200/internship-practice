package com.springboot.springcorepractice.configuration;

import com.springboot.springcorepractice.service.Coach;
import com.springboot.springcorepractice.service.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SportConfig {

    @Bean
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
