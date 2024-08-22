package com.library.aspect;

import java.time.LocalTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(* com.library.service..*(..))")
    public void serviceExecution(){}

    @Pointcut("execution(* com.library.repository..*(..))")
    public void repositoryExecution(){}

    @After("serviceExecution() || repositoryExecution()")
    public void methodTimeStamp(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().toShortString();
        LocalTime now = LocalTime.now();
        System.out.println("Method " + methodName + " executed at " + now);
    }

    @AfterThrowing("serviceExecution() || repositoryExecution()")
    public void throwThis(){
        System.out.println("Something happened!");
    }
}
