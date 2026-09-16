package com.aloha.jpql.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.jpql.domain.OrderStatus;
import com.aloha.jpql.domain.Orders;
import com.aloha.jpql.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  @Override
  public List<Orders> findByUserNo(Long userNo) {
    return orderRepository.findByUserNo(userNo);
  }

  @Override
  public Orders findDetail(Long no) {
    return orderRepository.findDetail(no).orElse(null);
  }

  @Override
  public List<Object[]> countByStatus() {
    return orderRepository.countByStatus();
  }

  @Override
  public List<Orders> findByStatus(OrderStatus status) {
    return orderRepository.findByStatus(status);
  }

  @Override
  public Orders findByNo(Long no) {
    return orderRepository.findById(no).orElse(null);
  }

  @Override
  public long count() {
    return orderRepository.count();
  }
  
}
