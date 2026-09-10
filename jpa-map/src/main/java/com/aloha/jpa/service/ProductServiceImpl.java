package com.aloha.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aloha.jpa.dao.ProductRepository;
import com.aloha.jpa.domain.Product;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ProductServiceImpl implements ProductService {
  // ⭐ 의존성 주입
  private final ProductRepository productRepository;

  @Override
  public List<Product> list() {
    return productRepository.findAll();
  }

  @Override
  public Product select(Integer no) {
    return productRepository.findById(no).orElse(null);
  }

  @Override
  public Product insert(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Product update(Product product) {
    Product old = productRepository.findById(product.getNo()).orElse(null);
    if( old == null )
      return null;
    old.setName(product.getName());
    old.setPrice(product.getPrice());
    return old;
  }

  @Override
  public void delete(Integer no) {
    productRepository.deleteById(no);
  }

  
  
}
