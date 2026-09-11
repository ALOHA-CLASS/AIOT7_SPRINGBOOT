package com.aloha.jpa;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.aloha.jpa.dao.OrderItemRepository;
import com.aloha.jpa.domain.OrderItem;
import com.aloha.jpa.domain.Orders;
import com.aloha.jpa.domain.Product;
import com.aloha.jpa.dto.OrderItemRequest;
import com.aloha.jpa.service.OrderItemService;
import com.aloha.jpa.service.OrderService;
import com.aloha.jpa.service.ProductService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@SpringBootTest 
@Transactional 
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)    
public class OrderTest {

  @Autowired ProductService productService;
  @Autowired OrderService orderService;
  @Autowired OrderItemService orderItemService;
  @Autowired OrderItemRepository orderItemRepository;

  // 상품 샘플 데이터
  private static final String[] PRODUCT_NAMES = {
    "생삼겹살", "특목살", "생갈비", "갈매기살", "항정살"
  };

  private static final int[] PRODUCT_PRICES = {
    15000, 15000, 15000, 15000, 16000
  };

  private static final int[] ORDER_ITEM_QUANTITIES = {1, 2, 3};

  @Test 
  void 주문테스트() {
    // 1. 상품 5개 등록
    List<Product> savedProducts = new ArrayList<>();
    for (int i = 0; i < PRODUCT_NAMES.length; i++) {
        Product product = new Product();
        product.setName(PRODUCT_NAMES[i]);
        product.setPrice(PRODUCT_PRICES[i]);
        savedProducts.add(productService.insert(product));
    }

    // 5개 모두 저장되고 DB가 채번한 PK(no)가 부여되었는지 확인
    assertThat(savedProducts).hasSize(5);
    assertThat(savedProducts.get(0).getNo()).isNotNull();
    assertThat(savedProducts.get(0).getName()).isEqualTo("생삼겹살");

    // 2. 주문 생성 ("첫 상품명 외 N개" 형식의 주문명)
    int orderItemCount = ORDER_ITEM_QUANTITIES.length;
    Orders order = new Orders();
    order.setOrderName(
        savedProducts.get(0).getName() + " 외 " + (orderItemCount - 1) + "개"
    );
    // 15000x1, 15000x2, 15000x3 : 15000+30000+4500=90000
    order.setTotalAmount(90000);

    Orders savedOrder = orderService.insert(order);

    // 주문이 저장되고 주문명이 의도한 포맷대로 조합되었는지 확인
    assertThat(savedOrder.getNo()).isNotNull();
    assertThat(savedOrder.getOrderName()).isEqualTo("생삼겹살 외 2개");

    // 3. 주문항목 3개 등록 (수량 1, 2, 3)
    List<OrderItem> savedItems = new ArrayList<>();
    for (int i = 0; i < orderItemCount; i++) {
        OrderItemRequest request = new OrderItemRequest();
        request.setOrderNo(savedOrder.getNo());
        request.setProductNo(savedProducts.get(i).getNo());
        request.setPrice(PRODUCT_PRICES[i]);
        request.setQuantity(ORDER_ITEM_QUANTITIES[i]);

        savedItems.add(orderItemService.insert(request));
    }

    // 주문항목 3개가 각각 의도한 수량(1, 2, 3)으로 저장되었는지 확인
    assertThat(savedItems).hasSize(orderItemCount);
    for (int i = 0; i < orderItemCount; i++) {
        assertThat(savedItems.get(i).getQuantity())
                .isEqualTo(ORDER_ITEM_QUANTITIES[i]);
    }


    // 4. 주문 조회 (총액 검증) - 서비스가 누적한 totalAmount와 비교할 기대값 계산
    int expectedTotalAmount = 0;
    for (int i = 0; i < orderItemCount; i++) {
        expectedTotalAmount += PRODUCT_PRICES[i] * ORDER_ITEM_QUANTITIES[i];
    }

    Orders selectedOrder = orderService.select(savedOrder.getNo());
    // 생성된 주문의 주문번호 확인
    log.info("생성된 주문번호 : {}", savedOrder.getNo());
    // 조회한 주문 정보 확인
    log.info("조회한 주문 : {}", selectedOrder);
    // 생성된 주문의 주문항목 리스트 확인
    log.info("주문항목 리스트 : {}", selectedOrder.getOrderItems());

    assertThat(selectedOrder).isNotNull();
    // ⭐ 검증 안 된 문제
    // assertThat(selectedOrder.getOrderItems()).hasSize(orderItemCount);
    assertThat(selectedOrder.getTotalAmount()).isEqualTo(expectedTotalAmount);

    // 5. 주문상품 조회
    OrderItem selectedItem =
            orderItemService.select(savedItems.get(0).getNo());

    // 주문항목에 연관된 상품/주문이 올바르게 매핑되어 있는지 확인
    assertThat(selectedItem).isNotNull();
    assertThat(selectedItem.getProduct().getName()).isEqualTo("생삼겹살");
    assertThat(selectedItem.getOrder().getNo()).isEqualTo(savedOrder.getNo());

     // 6. 주문별 주문상품 조회
    assertThat(
        orderItemService.listByOrderNo(savedOrder.getNo())
    ).hasSize(orderItemCount);

    // 실제 Repository에도 저장되었는지 최종 확인
    assertThat(orderItemRepository.count()).isEqualTo(orderItemCount);
  }
  
}
