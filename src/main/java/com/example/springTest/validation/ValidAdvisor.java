package com.example.springTest.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //basePackageClasses = 속성으로 특정 컨트롤러(클래스)에만 적용 가능
public class ValidAdvisor {
  @ExceptionHandler(value = Exception.class)  //Global 예외처리 방식인데, 지역 예외는 해당 컨트롤러 안에 넣으면 됨. (우선순위 : 지역예외 > Global 예외)
  public ResponseEntity exception(Exception e){
    System.out.println("---------");
    System.out.println(e.getClass().getName());
    System.out.println("---------");

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
  }
}
