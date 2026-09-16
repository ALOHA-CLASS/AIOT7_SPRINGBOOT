package com.aloha.jpql.service;

import java.util.List;

import com.aloha.jpql.domain.Product;

public interface ProductService {
  List<Product> findAll();
  List<Product> serach(String keyword);
  Product findByNo(Long no);
  long count();
}
