package com.aloha.jpa.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity 
public class Product {

  @Id   // PK 지정
  @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
  private Integer no;
  @Column(unique = true, length = 36)     // VARCHAR(36)
  private String id;
  @Column(length = 100, nullable = false) // VARCHAR(100) NOT NULL
  private String name;
  private int price;
  
  @Column(
    columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
    insertable = false,
    updatable = false
  )
  private LocalDateTime createdAt;

  @Column(
    columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
    insertable = false,
    updatable = false
  )
  private LocalDateTime updatedAt;

  public Product() {
    this.id = UUID.randomUUID().toString();
  }
  
}
