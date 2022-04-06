package com.example.springTest.aop;

import com.example.springTest.dto.CarRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {
  @Pointcut("execution(* com.example.springTest.controller..*.*(..))")
  private void pointcut(){}
  @Pointcut("@annotation(com.example.springTest.annotation.Decode)")
  private void enableDecode(){}

  @Before("pointcut() && enableDecode()")
  public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
    Object[] args = joinPoint.getArgs();
    for(Object arg : args){
      if(arg instanceof CarRequest){
        CarRequest carRequest = CarRequest.class.cast(arg);
        String carNumber = new String(Base64.getDecoder().decode(carRequest.getCarNumber()), "UTF-8");
        carRequest.setCarNumber(carNumber);
      }
    }
  }

  @AfterReturning(value = "pointcut() && enableDecode()", returning = "returnObj")
  public void afterReturn(JoinPoint joinPoint, Object returnObj){
    System.out.println(returnObj);
    if(returnObj instanceof CarRequest){

      CarRequest carRequest = CarRequest.class.cast(returnObj);
      String carNumber = Base64.getEncoder().encodeToString(carRequest.getCarNumber().getBytes());
      System.out.println(carNumber);
      carRequest.setCarNumber(carNumber);
    }
  }

}
