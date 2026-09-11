package com.aloha.request.dto;

import java.util.Date;

import lombok.Data;

@Data 
public class Product {
  private Integer no;
  private String id;
  private String name;
  private int price;
  private Date createdAt;
  private Date updatedAt;
}
