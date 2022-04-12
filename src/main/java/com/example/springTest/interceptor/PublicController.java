package com.example.springTest.interceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api4")
public class PublicController {
  @GetMapping("/hello")
  public String hello(){
    return "hello? api4";
  }
}
