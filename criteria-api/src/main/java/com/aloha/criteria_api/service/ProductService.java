package com.aloha.criteria_api.service;

import java.util.List;

import com.aloha.criteria_api.domain.Product;

public interface ProductService {

  List<Product> findAll();        // 전체 목록
  Product findByNo(Long no);      // 단일 상품 조회
  long count();                   // 상품 개수

  // 상품 검색
  List<Product> search(
    String name, Integer minPrice, Integer maxPrice, Integer minStock
  );
  
}
