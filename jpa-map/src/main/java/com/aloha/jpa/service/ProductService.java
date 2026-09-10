package com.aloha.jpa.service;

import java.util.List;

import com.aloha.jpa.domain.Product;

public interface ProductService {
  // 상품 CRUD
  List<Product> list();
  Product select(Integer no);
  Product insert(Product product);
  Product update(Product product);
  void delete(Integer no);
  
}
