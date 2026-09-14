package com.aloha.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.mvc.domain.Posts;
import com.aloha.mvc.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;





@Slf4j 
@Controller 
@RequiredArgsConstructor 
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;

  /**
   * 게시글 목록
   * @return
   */
  @GetMapping("")
  public String list() {
    return "posts/list";
  }

  /**
   * 게시글 조회
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public String read(@PathVariable("id") String id) {
    // TODO: 데이터 조회
    return "posts/read";
  }

  /**
   * 게시글 등록 화면
   * @return
   */
  @GetMapping("/create")
  public String create() {
    return "posts/create";
  }

  /**
   * 게시글 등록 처리
   * @param post
   * @return
   */
  @PostMapping("")
  public String createPost(Posts post) {
    // TODO: 게시글 등록 처리
    return "redirec:/posts";
  }
  
  /**
   * 게시글 수정
   * @param id
   * @return
   */
  @GetMapping("/{id}/update")
  public String update(@PathVariable("id") String id) {
    // TODO: 데이터 조회
    return "posts/update";
  }

  /**
   * 게시글 수정 처리
   * @param post
   * @return
   */
  @PostMapping("/update")
  public String updatePost(Posts post) {
    // TODO: 데이터 수정 처리
    return "redirect:/posts";
  }
  
  /**
   * 게시글 삭제 처리
   * @param id
   * @return
   */
  @PostMapping("/delete")
  public String delete(@RequestParam("id") String id) {
    // TODO: 데이터 삭제 처리
    return "redirect:/posts";
  }
  
  

  
}
