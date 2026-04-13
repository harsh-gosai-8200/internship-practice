package com.springboot.spring_aop;

import com.springboot.spring_aop.dao.Account;
import com.springboot.spring_aop.dao.AccountDAO;
import com.springboot.spring_aop.dao.MembershipDAO;
import com.springboot.spring_aop.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAopApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO, TrafficFortuneService trafficFortuneService){
        return runner -> {

//            demoTheBeforeAdvice(accountDAO, membershipDAO);
//            demoTheAfterReturnAdvice(accountDAO);
//            demoTheAfterThrowingAdvice(accountDAO);
//            demoTheAfterAdvice(accountDAO);
//            demoTheAroundAdvice(trafficFortuneService);
//            demoTheAroundAdviceHandleException(trafficFortuneService);
            demoTheAroundAdviceRethrowException(trafficFortuneService);
        };
    }

    private void demoTheAroundAdviceRethrowException(TrafficFortuneService trafficFortuneService) {
        System.out.println("main program : demoTheAroundAdviceHandleException");
        System.out.println("Calling getFortune()");
        boolean trigger = true;
        String data = trafficFortuneService.getFortune(trigger);
        System.out.println("fortune is : " + data);
        System.out.println("Finished");
    }

    private void demoTheAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {
        System.out.println("main program : demoTheAroundAdviceHandleException");
        System.out.println("Calling getFortune()");
        boolean trigger = true;
        String data = trafficFortuneService.getFortune(trigger);
        System.out.println("fortune is : " + data);
        System.out.println("Finished");
    }

    private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {
        System.out.println("main program : demoTheAroundAdvice");
        System.out.println("Calling getFortune()");
        String data = trafficFortuneService.getFortune();
        System.out.println("fortune is : " + data);
        System.out.println("Finished");
    }

    private void demoTheAfterAdvice(AccountDAO accountDAO) {
        List<Account> accountList = null;
        try{
            boolean trigger = false;
            accountList = accountDAO.findAccount(trigger);
        } catch (Exception e) {
            System.out.println("\n Main Program : caught exception..." + e);
        }

        System.out.println("\nMain progaram : demoTheAfterReturningAdvice");
        System.out.println(accountList);
    }

    private void demoTheAfterThrowingAdvice(AccountDAO accountDAO) {
        List<Account> accountList = null;
        try{
            boolean trigger = true;
            accountList = accountDAO.findAccount(trigger);
        } catch (Exception e) {
            System.out.println("\n Main Program : caught exception..." + e);
        }

        System.out.println("\nMain progaram : demoTheAfterReturningAdvice");
        System.out.println(accountList);
    }

    private void demoTheAfterReturnAdvice(AccountDAO accountDAO) {
        List<Account> accountList = accountDAO.findAccount();
        System.out.println("\nMain progaram : demoTheAfterReturningAdvice");
        System.out.println(accountList);

    }

    private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
        accountDAO.addAccount();
        membershipDAO.setId(1);
        membershipDAO.getId();
        membershipDAO.addSillyMember();

    }
}
