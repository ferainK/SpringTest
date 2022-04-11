package com.example.springTest.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api2")
//@Validated : 해당 컨트롤러에서 Validate 속성(Min, NotNull 등 지정 가능), 변수 앞에 @Valid를 넣으면 Validate 검증 후 매개변수로 받는다는 의미
public class ValApiController {
  @PostMapping("")
  public ResponseEntity user(@Valid @RequestBody DtoUser user, BindingResult bindingResult){
    if(bindingResult.hasErrors()){
      StringBuilder sb = new StringBuilder();
      bindingResult.getAllErrors().forEach(objectError -> {
        FieldError field = (FieldError) objectError;
        String msg = objectError.getDefaultMessage();

        System.out.println("[오류내용]");
        System.out.println("field : " + field.getField());
        System.out.println("Message : " + msg);

        sb.append("field : " + field.getField());
        sb.append(", Message : " + msg);
      });
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
    }

    System.out.println(user);

    if(user.getAge() >=  90){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
    }
    return ResponseEntity.ok(user);
  }

  @GetMapping("")
  @Validated
  public DtoUser get(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age){
    DtoUser user = new DtoUser();
    user.setName(name);
    user.setAge(age);

    int a = 10 + age;
    return user;
  }

  //지역 예외 처리
  @ExceptionHandler(value = ConstraintViolationException.class)
  public ResponseEntity constraintViolationException(ConstraintViolationException e){
    e.getConstraintViolations().forEach(error ->{
      System.out.println(error);
    });
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }
  @ExceptionHandler(value = MissingServletRequestParameterException.class)
  public ResponseEntity missingServletRequestParameterException(MissingServletRequestParameterException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }
  @ExceptionHandler(value = NullPointerException.class)
  public ResponseEntity nullPointerException(NullPointerException e){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }
}
