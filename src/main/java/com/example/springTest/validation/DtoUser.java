package com.example.springTest.validation;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DtoUser {
  @NotBlank
  private String name;
  @Min(value = 1)
  private int age;
  @Email
  private String email;
  @JsonProperty("phone_number")
  @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식이 옳바르지 않습니다. (01x-xxxx-xxxx)")
  private String phoneNumber;
  @JsonProperty("req_year_month")
  @YearMonth
  private String reqYearMonth; //yyyyMM
  //객체를 불러오는 경우 @Valid 를 붙여줘야만함!

  public String getReqYearMonth() {
    return reqYearMonth;
  }

  public void setReqYearMonth(String reqYearMonth) {
    this.reqYearMonth = reqYearMonth;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public String toString() {
    return "DtoUser{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", email='" + email + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        '}';
  }
}
