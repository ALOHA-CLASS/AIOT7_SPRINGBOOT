package com.aloha.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.jpa.dao.OrderRepository;
import com.aloha.jpa.domain.Orders;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Transactional
public class OrderServiceImpl implements OrderService {
  
  // ⭐ 사용할 Repository 를 의존성 주입
  private final OrderRepository orderRepository;

  @Override
  public List<Orders> list() {
    return orderRepository.findAll();
  }

  @Override
  public Orders select(Integer no) {
    // - findById(PK)       : 기본키를 조건으로 조회하여 Optional<엔터티> 반환
    // - orElse(없을때 값)   : 조회한 데이터 없으면, 대체할 객체를 지정
    Orders orders = orderRepository.findById(no).orElse(null);
    if( orders == null )
      return null;
    return orders;
  }

  @Override
  public Orders insert(Orders orders) {
    return orderRepository.save(orders);
  }
  
  @Override
  public int update(Orders orders) {
    Orders old = orderRepository.findById(orders.getNo()).orElse(null);
    if( old == null )
      return 0;
    old.setOrderName(orders.getOrderName());
    old.setTotalAmount(orders.getTotalAmount());
    return orderRepository.save(old) != null ? 1 : 0;
  }

  @Override
  public int delete(Integer no) {
    orderRepository.deleteById(no);
    return 1;
  }


}
