package com.example.springTest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {

  @Pointcut("execution(* com.example.springTest.controller..*.*(..))")
  private void pointcut(){}
  @Pointcut("@annotation(com.example.springTest.annotation.Timer)")
  private void enableTimer(){}

  @Around("pointcut() && enableTimer()")
  public void around(ProceedingJoinPoint joinPoint) throws Throwable {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    Object result = joinPoint.proceed();

    stopWatch.stop();
    System.out.println("Total time : " + stopWatch.getTotalTimeSeconds());

  }

}
