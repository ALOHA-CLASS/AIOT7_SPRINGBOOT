package com.aloha.jpa.controller;

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

import com.aloha.jpa.domain.OrderItem;
import com.aloha.jpa.dto.OrderItemRequest;
import com.aloha.jpa.service.OrderItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RestController 
@RequestMapping("/order-items")
@RequiredArgsConstructor 
public class OrderItemController {
  // ⭐ 의존성 주입
  private final OrderItemService orderItemService;

  // 🍃sp-crud (Spring Code Generator)
  
  @GetMapping()
  public ResponseEntity<?> getAll() {
      try {
          List<OrderItem> orderItems = orderItemService.list();
          return new ResponseEntity<>(orderItems, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @GetMapping("/{no}")
  public ResponseEntity<?> getOne(@PathVariable("no") Integer no) {
      try {
          OrderItem orderItem = orderItemService.select(no);
          return new ResponseEntity<>(orderItem, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @PostMapping()
  public ResponseEntity<?> create(@RequestBody OrderItemRequest orderItemRequest) {
      try {
          OrderItem newOrderItem = orderItemService.insert(orderItemRequest);
          return new ResponseEntity<>(newOrderItem, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @PutMapping()
  public ResponseEntity<?> update(@RequestBody OrderItem orderItem) {
      try {
          OrderItem updateOrderItem = orderItemService.update(orderItem);
          return new ResponseEntity<>(updateOrderItem, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @DeleteMapping("/{no}")
  public ResponseEntity<?> destroy(@PathVariable("no") Integer no) {
      try {
          orderItemService.delete(no);
          return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  
}
