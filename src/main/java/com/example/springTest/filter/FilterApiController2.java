package com.example.springTest.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j  //로그 변수 사용
@RestController
@RequestMapping("/api31")
public class FilterApiController2 {
  @PostMapping("")
  public FilterUser user(@RequestBody FilterUser user){
    log.info("user : {}", user);
    return user;
  }
}
