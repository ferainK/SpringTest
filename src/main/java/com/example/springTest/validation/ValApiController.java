package com.example.springTest.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api2")
public class ValApiController {
  @PostMapping("/user")
  public ResponseEntity user(@Valid @RequestBody DtoUser user, BindingResult bindingResult){
    if(bindingResult.hasErrors()){

    }

    System.out.println(user);

    if(user.getAge() >=  90){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
    }
    return ResponseEntity.ok(user);
  }
}
