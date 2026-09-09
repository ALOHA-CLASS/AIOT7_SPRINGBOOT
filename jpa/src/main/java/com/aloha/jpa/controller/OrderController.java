package com.aloha.jpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aloha.jpa.domain.Orders;
import com.aloha.jpa.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;




@Slf4j 
@RestController 
@RequestMapping("/orders")
@RequiredArgsConstructor 
public class OrderController {

  private final OrderService orderService;

  // 주문 목록
  @GetMapping
  public List<Orders> list() {
      return orderService.list();
  }

  // 주문 조회
  @GetMapping("/{no}")
  public Orders select(@PathVariable("no") Integer no) {
      Orders orders = orderService.select(no);
      return orders;
  }
  
  // 주문 등록
  @PostMapping
  public Orders insert(@RequestBody Orders order) {
      Orders newOrder = orderService.insert(order);
      return newOrder;
  }
  
  // 주문 수정
  @PutMapping
  public String update(@RequestBody Orders order) {
      int result = orderService.update(order);
      if( result > 0 )  {
        return "SUCCESS";
      }
      return "FAIL";
  }

  // 주문 삭제
  @DeleteMapping("/{no}")
  public void delete(@PathVariable("no") Integer no) {
    orderService.delete(no);
  }
  
}
