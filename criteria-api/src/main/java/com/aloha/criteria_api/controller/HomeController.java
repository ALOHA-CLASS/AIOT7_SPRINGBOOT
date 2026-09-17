package com.aloha.criteria_api.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.criteria_api.domain.Product;
import com.aloha.criteria_api.service.ProductService;

import lombok.RequiredArgsConstructor;



@Controller 
@RequiredArgsConstructor 
public class HomeController {

  private final ProductService productService;

  /**
   * 메인 화면
   * @param model
   * @return
   */
  @GetMapping("/")
  public String home(Model model) {
    // 상품 개수 조회해서 모델에 등록
    model.addAttribute("productCount", productService.count());

    // index.html 화면 지정
    return  "index";
  }
  
  /**
   * 상품 목록 화면
   * @param name
   * @param minPrice
   * @param maxPrice
   * @param minStock
   * @param model
   * @return
   */
  @GetMapping("/products")
  public String products(
    @RequestParam(name = "name", required = false) String name,
    @RequestParam(name = "minPrice", required = false) Integer minPrice,
    @RequestParam(name = "maxPrice", required = false) Integer maxPrice,
    @RequestParam(name = "minStock", required = false) Integer minStock,
    Model model
  ) {
    // 상품 검색
    List<Product> products = productService.search(name, minPrice, maxPrice, minStock);

    // 모델에 데이터 등록
    model.addAttribute("products", products);
    model.addAttribute("name", name);
    model.addAttribute("minPrice", minPrice);
    model.addAttribute("maxPrice", maxPrice);
    model.addAttribute("minStock", minStock);

    // products.html 화면 지정
    return "products";
  }
  
}
