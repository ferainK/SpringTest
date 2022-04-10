package com.example.springTest.validation;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class DtoUser {
  private String name;
  private int age;
  @Email
  private String email;
  @Pattern(regexp = "^\\\\d{2,3}-\\\\d{3,4}-\\\\d{4}\\$")
  @JsonProperty("phone_number")
  private String phoneNumber;

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
