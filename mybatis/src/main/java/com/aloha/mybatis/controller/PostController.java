package com.aloha.mybatis.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aloha.mybatis.domain.Posts;
import com.aloha.mybatis.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RestController 
@RequestMapping("/posts")
@RequiredArgsConstructor 
public class PostController {

  private final PostService postService;

  
  @GetMapping()
  public ResponseEntity<?> getAll() {
      try {
          List<Posts> list = postService.list();
          return new ResponseEntity<>(list, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @GetMapping("/{no}")
  public ResponseEntity<?> getOne(@PathVariable("no") Integer no) {
      try {
          Posts post = postService.select(no);
          return new ResponseEntity<>(post, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @PostMapping()
  public ResponseEntity<?> create(@RequestBody Posts posts) {
      try {
          boolean result = postService.insert(posts);
          if(result)
            return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
          return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @PutMapping()
  public ResponseEntity<?> update(@RequestBody Posts posts) {
      try {
          boolean result = postService.update(posts);
          if(result)
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
          return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @DeleteMapping("/{no}")
  public ResponseEntity<?> destroy(@PathVariable("no") Integer no) {
      try {
          boolean result = postService.delete(no);
          if(result)
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
          return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
}
