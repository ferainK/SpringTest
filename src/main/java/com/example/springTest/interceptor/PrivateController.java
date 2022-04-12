package com.example.springTest.interceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api4private")
@Auth
public class PrivateController {
  @GetMapping("/hello")
  public String hello(){
    return "Hello? api4 in private!";
  }
}
