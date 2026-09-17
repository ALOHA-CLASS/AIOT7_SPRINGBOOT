package com.aloha.querydsl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.querydsl.domain.Product;
import com.aloha.querydsl.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Transactional (readOnly =true)
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public List<Product> findAll() {
    return productRepository.findAll();
  }

  @Override
  public Product findByNo(Long no) {
    return productRepository.findById(no).orElse(null);
  }

  @Override
  public long count() {
    return productRepository.count();
  }

  @Override
  public List<Product> search(String name, Integer minPrice, Integer maxPrice, Integer minStock) {
    return productRepository.searchByCriteria(name, minPrice, maxPrice, minStock);
  }


  
}
