package com.example.petapp.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.petapp.services.*.*(..))")
    public void logServiceMethods() {
        System.out.println("Executing a service method");
    }
}
