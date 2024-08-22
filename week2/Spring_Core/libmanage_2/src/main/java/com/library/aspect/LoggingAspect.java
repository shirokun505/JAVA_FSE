package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    @Pointcut("execution(* com.library.service..*(..))")
    public void serviceExecution(){}

    @Pointcut("execution(* com.library.repository..*(..))")
    public void repositoryExecution(){}

    @Before("serviceExecution() || repositoryExecution()")
    public void beforeExec(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("Method "+method+" started executing...");
    }

    @After("serviceExecution() || repositoryExecution()")
    public void AfterExec(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("...method "+method+" completed execution.");
    }


}
