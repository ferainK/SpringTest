package com.example.springTest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {

  @Pointcut("execution(* com.example.springTest.controller..*.*(..))")
  private void pointcut(){}

  @Before("pointcut()")
  public void Before(JoinPoint joinPoint){
    // 매소드 이름 출력하기
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    Method method = methodSignature.getMethod();
    System.out.println("method : " + method.getName());

    // 서비스 로직이 동작하면 수행할 내용들
    System.out.println("Before");
    Object[] args = joinPoint.getArgs();
    for(Object arg : args) {
      System.out.println("Object" + arg);
    }
  }

  @AfterReturning(value = "pointcut()", returning = "object")
  public void After(JoinPoint joinPoint, Object object){
    System.out.println("After");
    System.out.println(object);
  }
}
