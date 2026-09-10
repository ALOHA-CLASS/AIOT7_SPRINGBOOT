package com.aloha.jpa.dto;

import lombok.Data;

@Data 
public class OrderItemRequest {
  private String productName;
  private int price;
  private int quantity;
  private Integer orderNo;
  private Integer productNo;
}
