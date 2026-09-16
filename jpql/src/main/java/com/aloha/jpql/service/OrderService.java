package com.aloha.jpql.service;

import java.util.List;

import com.aloha.jpql.domain.OrderStatus;
import com.aloha.jpql.domain.Orders;

public interface OrderService {
    List<Orders> findByUserNo(Long userNo);
    Orders findDetail(Long no);
    List<Object[]> countByStatus();
    List<Orders> findByStatus(OrderStatus status);
    Orders findByNo(Long no);
    long count();
}
