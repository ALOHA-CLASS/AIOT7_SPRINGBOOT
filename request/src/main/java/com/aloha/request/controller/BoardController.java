package com.aloha.request.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aloha.request.dto.Board;
import com.aloha.request.dto.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
// 클라이언트의 요청 받아서 처리하는 역할 : Controller
@Controller       // 해당 클래스를 컨트롤러 역할로 빈등록 
public class BoardController {

  // * 컨트롤러 메소드
  @ResponseBody     // 데이터를 그대로 응답
  @GetMapping("/")  // [GET], URL: "/main" 요청 경로 매핑
  public String index() {
    return "메인경로";           // 뷰 이름 지정
  }

  // ⭐ 파라미터 받아오기
  // 🔗 http://localhost:8080/test?name=알로하&age=20&data=100
  // ?name=알로하&age=20 ➡ 쿼리스트링
  // name ➡ 파라미터, 알로하 ➡ 값
  // * @RequestParam("요청 파라미터명") 타입 파라미터명
  // * @RequestParam(name = "요청 파라미터명", defaultValue = "기본값", required = 필수여부) 타입 파라미터명
  @ResponseBody 
  @GetMapping("/test")
  public String param(
    @RequestParam("name") String name, 
    @RequestParam("age") Integer age,
    @RequestParam(name = "data", required = false) Integer data,
    @RequestParam(name = "money", defaultValue = "1000", required = false) Integer money
  ) {
    log.info("name : {}", name);
    log.info("age : {}", age);
    log.info("data : {}", data);
    log.info("money : {}", money);
    return "파라미터 받아오기";
  }

  // ⭐ 데이터 받아오는 방법 
  // 1. 요청 파라미터
  // 2. 경로 변수

  // ⚡ 요청 파라미터
  // 🔗 http://localhost:8080/board?no=100
  @ResponseBody 
  @GetMapping("/board")
  public String selectParam(@RequestParam("no") Integer no) {
    log.info("게시글 번호 : {}", no);
    return "게시글 번호 : " + no;
  }

  // ⚡ 경로 변수
  // 🔗 http://localhost:8080/board/100
  @ResponseBody 
  @GetMapping("/board/{no}")
  public String selectPathVariable(@PathVariable("no") Integer no) {
    log.info("게시글 번호 : {}", no);
    return "게시글 번호 : " + no;
  }


  // ⚡ 요청 파라미터
  // 🔗 http://localhost:8080/posts?id=2136914n13891h5-823678194
  @ResponseBody 
  @GetMapping("/posts")
  public String selectParam(@RequestParam("id") String id) {
    log.info("게시글 ID : {}", id);
    return "게시글 ID : " + id;
  }

  // ⚡ 경로 변수
  // 🔗 http://localhost:8080/posts/2136914n13891h5-823678194
  @ResponseBody 
  @GetMapping("/posts/{id}")
  public String selectPathVariable(@PathVariable("id") String id) {
    log.info("게시글 ID : {}", id);
    return "게시글 ID : " + id;
  }

  // 요청 데이터를 객체 바인딩
  // ⭐ POST 전송 데이터 종류
  // 1. JSON                            - @RequestBody ⭕
  // 2. Form  (<form method="post">)    - @RequestBody ❌

  // client --> Content-Type: application/json --> @RequestBody⭕ --> Board
  @ResponseBody 
  @PostMapping("/board")
  public String board(@RequestBody Board board) {
    log.info("Board : {}", board);
    return "요청 데이터 객체 바인딩";
  }
  
  // client --> Content-Type: multipart/form-data --> @RequestBody❌ --> Board
  @ResponseBody 
  @PostMapping("/posts")
  public String posts(Board board) {
    log.info("Board : {}", board);
    return "요청 데이터 객체 바인딩";
  }
  
  // ⭐ Content-Type 매핑
  // - 상품 데이터 등록 요청
  // - 🔗 http://localhost:8080/products
  // 1. JSON 매핑
  // 2. FORM 매핑
  // ⭐ consumes 속성에 매핑할 컨텐츠 타입을 지정

  // ⚡ Content-Type: application/json
  @ResponseBody 
  @PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String productsJSON(@RequestBody Product product) {
    log.info("상품정보 : {}", product);
    return "Content-Type 매핑 - JSON";
  }
  // ⚡ Content-Type: multipart/form-data
  @ResponseBody 
  @PostMapping(value = "/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String productsForm(Product product) {
    log.info("상품정보 : {}", product);
    return "Content-Type 매핑 - FORM";
  }

  // ⭐ 요청 헤더 받아오기
  // * @RequestHeader("헤더명") String 파라미터명
  @ResponseBody 
  @GetMapping("/header")
  public String header(
    @RequestHeader("User-Agent") String userAgent
  ) {
    log.info("User-Agent 헤더 : {}", userAgent);
    return "헤더 받아오기";
  }

  // ⭐ 쿠키 받아오기
  // * @CookieValue("쿠키명") String 파라미터명
  @ResponseBody 
  @GetMapping("/cookie")
  public String cookie(
    @CookieValue("username") String username
  ) {
    log.info("username : {}", username);
    return "쿠키 받아오기";
  }
  

  
  

  
}
