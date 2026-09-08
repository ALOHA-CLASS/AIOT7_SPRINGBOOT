package com.aloha.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloha.crud.dao.OrderRepository;
import com.aloha.crud.domain.Orders;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j   // 📜 로깅 설정
@Service // ⭐ 빈 등록
@RequiredArgsConstructor // final 로 선언된 필드의 생성자 자동 생성
public class OrderServiceImpl implements OrderService {

  // lombok 활용 ⭐ 의존성 자동 주입 - 생성자 주입
  private final OrderRepository orderRepository;

  // ⭐ 의존성 자동 주입 - 필드 주입
  // @Autowired 
  // private OrderRepository orderRepository;

  // ⭐ 의존성 자동 주입
  // @Autowired  // ⭐ 생성자 주입 시, @Autowired 생략 가능
  // public OrderServiceImpl(OrderRepository orderRepository) {
  //   this.orderRepository = orderRepository;
  // }

  @Override
  public List<Orders> list() {
    return orderRepository.list();
  }

  @Override
  public Orders select(Integer no) {
    return orderRepository.select(no);
  }

  @Override
  public Orders insert(Orders orders) {
    // 데이터 유효성 검사
    // - 주문명, 총금액
    String orderName = orders.getOrderName();
    if( orderName == null || orderName.trim().isEmpty() ) {
      // System.err.println("주문명이 비어있습니다!");
      log.error("주문명이 비어있습니다!");
      return null;
    }
    int totalAmount = orders.getTotalAmount();
    if( totalAmount < 0 ) {
      // System.err.println("총금액이 올바르지 않습니다!");
      log.error("총금액이 올바르지 않습니다!");
      return null;
    }
    return orderRepository.insert(orders);
  }

  @Override
  public int update(Orders orders) {
    Integer no = orders.getNo();
    if( no == null || no <= 0 ) {
      // System.err.println("주문 번호가 유효하지 않습니다!.");
      log.error("주문 번호가 유효하지 않습니다!.");
      return 0;
    }
    Orders checkOrders = select(no);
    if( checkOrders == null ) {
      // System.err.println("존재하지 않는 주문 정보입니다.");
      log.error("존재하지 않는 주문 정보입니다.");
      return 0;
    }
    return orderRepository.update(orders);
  }

  @Override
  public int delete(Integer no) {
    if( no == null || no <= 0 ) {
      // System.err.println("주문 번호가 유효하지 않습니다!");
      log.error("주문 번호가 유효하지 않습니다!.");
      return 0;
    }
    return orderRepository.delete(no);
  }

  
}
