package com.springboot.spring_aop.aspect;

import com.springboot.spring_aop.dao.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Aspect
@Component
public class MyDemoLoginAspect {

//    @Before("execution(public void com.springboot.spring_aop.dao.AccountDAO.addAccount())")
//    @Before("execution(public void updateAccount())")
//    @Before("execution(public void add*())")
    @Before("execution(* add*())")
    public void beforeAddAccountAdvice(){
        System.out.println("\n Execution @Before advice on updateAccount()");
    }

//    @Before("execute(public void com.springboot.spring_aop.dao.AccountDAO.set*(..))")
    @Before("execution(* com.springboot.spring_aop.dao.*.set*(..))")
    public void beforeSetAccountAdvice(){
        System.out.println("\n Execution @Before advice on setAccount()");
    }

    @Before("execution(* com.springboot.spring_aop.dao.*.get*(..))")
    public void beforeGetAccountAdvice(){
        System.out.println("\n Execution @Before advice on getAccount()");
    }

    @Pointcut("beforeAddAccountAdvice() && !(beforeSetAccountAdvice() || beforeGetAccountAdvice())")
    public void forDaoPackageNoGetterSetter(){}

    @AfterReturning(
            pointcut = "execution(* com.springboot.spring_aop.dao.AccountDAO.findAccount(..))",
            returning = "results")
    public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> results){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("Executing @AfterReturning on method : " + method);
        System.out.println("Result is : " + results);
        convertTheAccountNameToUpperCase(results);
        System.out.println("Result is : " + results);
    }

    private void convertTheAccountNameToUpperCase(List<Account> results) {
        for(Account account : results){
            String theUpperName = account.getName().toUpperCase();
            account.setName(theUpperName);
        }
    }

    @AfterThrowing(
            pointcut = "execution(* com.springboot.spring_aop.dao.AccountDAO.findAccount(..))",
            throwing = "theExe")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable theExe){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("Executing @AfterThrowing on method : " + method);
        System.out.println("Exception is : " + theExe);
    }

    @After("execution(* com.springboot.spring_aop.dao.AccountDAO.findAccount(..))")
    public void afterFinallyFoundAdvice(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("Executing @After (finally) on method : " + method);
    }

    @Around("execution(* com.springboot.spring_aop.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("Executing @Around (finally) on method : " + method);
        long begin = System.currentTimeMillis();
        Object result = null;
        try{
            result = proceedingJoinPoint.proceed();
        }catch(Exception e){
//            System.out.println(e.getMessage());
//            result = "Major accident ! But no worries, your private AOP helicopter is on the way !!";
            throw e;
        }
        long end = System.currentTimeMillis();
        long duration = end-begin;
        System.out.println("Duration : " + duration/1000.0 + " seconds");
        return result;
    }
}


