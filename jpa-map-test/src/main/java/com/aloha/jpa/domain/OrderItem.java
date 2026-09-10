package com.aloha.jpa.domain;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data 
@Entity 
public class OrderItem {

  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer no;           // 주문항목 번호
  private String id;            // 주문항목 ID
  // private String productName;   // 상품명
  private int price;            // 단가
  private int quantity;         // 수량

  // 주문항목 : 주문 = N : 1
  @ManyToOne 
  @JoinColumn(name = "order_no")    // 외래키(FK) 지정
  @JsonIgnore                       // JSON 응답에서 제외
  private Orders order;

  // 주문항목 : 상품 = N : 1
  @ManyToOne                        
  @JoinColumn(name = "product_no")  // 외래키 지정
  private Product product;

  public OrderItem() {
    this.id = UUID.randomUUID().toString();
  }
}
