package com.example.springTest.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {
  @ExceptionHandler(AuthException.class)
  public ResponseEntity authException(){
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }
}
