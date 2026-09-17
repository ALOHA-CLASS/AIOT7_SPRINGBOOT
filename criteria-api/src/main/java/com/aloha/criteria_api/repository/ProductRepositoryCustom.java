package com.aloha.criteria_api.repository;

import java.util.List;

import com.aloha.criteria_api.domain.Product;

/**
 * JPA Criteria API 를 이용해서 동적쿼리 기능 사용해보기
 * 
 */
public interface ProductRepositoryCustom {

  // 상품명 부분 검색 (LIKE)
  List<Product> findByContaining(String keyword);

  // 가격 범위 검색
  List<Product> findByPriceBetween(int minPrice, int maxPrice);

  // 가격 내림차순 전체 조회
  List<Product> findAllOrderByPriceDesc();
  
  // 상품명/최소가격/최대가격/최소재고 조건으로 조회
  // * 각 파라미터가 null이거나 비어있으면 무시
  List<Product> searchByCriteria(
    String name, Integer minPrice, Integer maxPrice, Integer minStock
  );
}
