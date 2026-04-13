package com.springboot.mvc_crud.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.springboot.mvc_crud.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.springboot.mvc_crud.services.*.*(..))")
    private void forServicesPackage(){}

    @Pointcut("forControllerPackage() || forServicesPackage()")
    private void forAppFlow(){}

    @Before("forAppFlow")
    public void before(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        logger.info("In @Before : calling method is " + method);
    }
}
