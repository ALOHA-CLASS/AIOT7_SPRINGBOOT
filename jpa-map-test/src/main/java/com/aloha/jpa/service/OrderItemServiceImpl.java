package com.aloha.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.jpa.dao.OrderItemRepository;
import com.aloha.jpa.dao.OrderRepository;
import com.aloha.jpa.dao.ProductRepository;
import com.aloha.jpa.domain.OrderItem;
import com.aloha.jpa.domain.Orders;
import com.aloha.jpa.domain.Product;
import com.aloha.jpa.dto.OrderItemRequest;

import lombok.RequiredArgsConstructor;

@Service                            // Service 계층의 빈으로 등록
@RequiredArgsConstructor            // final 변수 생성자 자동 생성
@Transactional                      // 트랜잭션
public class OrderItemServiceImpl implements OrderItemService {

  // ⭐의존성 주입
  private final OrderItemRepository orderItemRepository;
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;

  @Override
  public List<OrderItem> list() {
    return orderItemRepository.findAll();
  }

  @Override
  public List<OrderItem> listByOrderNo(Integer no) {
    return orderItemRepository.findByOrderNo(no);
  }

  @Override
  public OrderItem select(Integer no) {
    return orderItemRepository.findById(no).orElse(null);
  }

  @Override
  public OrderItem insert(OrderItemRequest dto) {
    // DTO 롤 전달받은 주문번호(orderNo)로 Orders 엔터티를 조회
    Orders order = orderRepository.findById(dto.getOrderNo()).orElse(null);
    Product product = productRepository.findById(dto.getProductNo()).orElse(null);
    

    // DTO 로 전달받은 데이터를 OrderItem 엔터티로 세팅
    OrderItem orderItem = new OrderItem();
    orderItem.setPrice(dto.getPrice());
    orderItem.setQuantity(dto.getQuantity());
    // 조회된 Orders 엔터티를 OrderItem 에 세팅
    // OrderItem : Orders = N : 1 로 매핑해두어서 (order_no) 외래키로 저장됨.
    // order 객체의 no 를 OrderItem 의 orderNo 로 연결.
    orderItem.setOrder(order);
    orderItem.setProduct(product);

    return orderItemRepository.save(orderItem);
  }

  @Override
  @Transactional 
  public OrderItem update(OrderItem orderItem) {
    OrderItem old = orderItemRepository.findById(orderItem.getNo()).orElse(null);
    if( old == null ) 
      return null;
    // 변경
    old.setPrice(orderItem.getPrice());
    old.setQuantity(orderItem.getQuantity());
    // ⭐ @Transactional 안에서는 엔터티 변경감지를 하기 때문에
    // save() 메소드 호출하지 않아도 자동으로 UPDATE
    // return orderItemRepository.save(old);    // save호출 생략가능
    return old;
  }

  @Override
  public void delete(Integer no) {
    orderItemRepository.deleteById(no);
  }
  
  
}
