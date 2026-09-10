package com.aloha.jpa.service;

import java.util.List;

import com.aloha.jpa.domain.Orders;

public interface OrderService {
  
  // 주문 목록
  List<Orders> list();

  // 주문 조회
  Orders select(Integer no);

  // 주문 등록
  Orders insert(Orders orders);

  // 주문 수정
  int update(Orders orders);

  // 주문 삭제
  int delete(Integer no);

}
