package com.aloha.mvc.domain;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data 
@Entity 
public class Posts {

  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
  private Long no;
  @Column(unique = true, length = 36)     // VARCHAR(36)
  private String id;
  private String title;
  private String writer;
  @Column(columnDefinition = "TEXT")
  private String content;

  @Column(
    columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
    insertable = false,
    updatable = false
  )
  private Date createdAt;

  @Column(
    columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
    insertable = false,
    updatable = false
  )
  private Date updatedAt;
  
  public Posts() {
    this.id = UUID.randomUUID().toString();
  }

}
