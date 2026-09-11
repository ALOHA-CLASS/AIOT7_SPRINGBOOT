package com.aloha.response.dto;

import lombok.Data;

@Data 
public class Student extends Person {
  private String grade;
  private String stdNo;

  public Student() {
    super();
    this.grade = "4";
    this.stdNo = "20260001";
  }

  
}
