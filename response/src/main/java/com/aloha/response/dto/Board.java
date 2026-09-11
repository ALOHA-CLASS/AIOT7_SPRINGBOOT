package com.aloha.response.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class Board {

  private Integer no;       // 글번호
  private String id;        // ID
  private String title;     // 제목
  private String writer;    // 작성자
  private String content;   // 내용
  private Date createdAt;   // 등록일자
  private Date updatedAt;   // 수정일자

  public Board(String title, String writer, String content) {
    this.title = title;
    this.writer = writer;
    this.content = content;
    this.createdAt = new Date();
    this.updatedAt = new Date();
  }

  public Board(Integer no, String title, String writer, String content) {
    this.no = no;
    this.title = title;
    this.writer = writer;
    this.content = content;
    this.createdAt = new Date();
    this.updatedAt = new Date();
  }
}
