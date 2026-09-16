package com.aloha.jpql.domain;

// 주문 상태 열거타입
public enum OrderStatus {
  
  // 결제대기, 결제완료, 배송중, 배송완료, 주문취소
  READY, PAID, SHIPPING, COMPLETE, CANCEL
  
}
