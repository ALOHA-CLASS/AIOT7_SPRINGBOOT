package com.aloha.jpql.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.aloha.jpql.domain.Cart;
import com.aloha.jpql.domain.Product;
import com.aloha.jpql.domain.Users;
import com.aloha.jpql.service.CartService;
import com.aloha.jpql.service.OrderService;
import com.aloha.jpql.service.ProductService;
import com.aloha.jpql.service.UserService;

import lombok.RequiredArgsConstructor;



@Controller 
@RequiredArgsConstructor 
public class HomeController {

  private final UserService userService;
  private final ProductService productService;
  private final CartService cartService;
  private final OrderService orderService;

  /**
   * 메인 화면
   * @param model
   * @return
   */
  @GetMapping("/")
  public String home(Model model) {
    // 메인 화면에서 각 엔터티의 개수를 확인
    model.addAttribute("userCount", userService.count());
    model.addAttribute("productCount", productService.count());
    model.addAttribute("cartCount", cartService.count());
    model.addAttribute("orderCount", orderService.count());
    return "index";
  }

  /**
   * 사용자 목록 화면
   * @param keyword
   * @param model
   * @return
   */
  @GetMapping("/users")
  public String users(@RequestParam(name = "keyword", defaultValue = "") String keyword, Model model) {
    // 검색어 확인
    boolean check = keyword.isBlank();
    List<Users> users = null;
    // 검색어가 없으면
    if( check )
      // 전체 조회
      users = userService.findAll();
    else
      // 검색어로 조회
      users = userService.search(keyword);
    // 모델에 사용자 목록 등록
    model.addAttribute("users", users);
    // 화면 : users.html 지정
    return "users";
  }

  /**
   * 상품 목록 화면
   * @param keyword
   * @param model
   * @return
   */
  @GetMapping("/products")
  public String products(
    @RequestParam(name = "keyword", defaultValue = "") String keyword,
    Model model
  ) {
    // 검색어 확인
    boolean check = keyword.isBlank();
    List<Product> products = null;
    // 검색어가 없으면
    if( check )
      // 전체 조회
      products = productService.findAll();
    else
      // 검색어로 조회
      products = productService.serach(keyword);
    // 모델 등록
    model.addAttribute("products", products);
    // 화면 : products.html 지정
    return "products";
  }

  /**
   * 장바구니 화면
   * @param param
   * @return
   */
  @GetMapping("/carts/{userNo}")
  public String cart(@PathVariable("userNo") Long userNo, Model model) {
    Cart cart = cartService.findByUserNo(userNo);
    model.addAttribute("cart", cart);
    model.addAttribute("user", userService.findByNo(userNo));
    return "cart";
  }
  

  /**
   * 주문 목록 화면
   * @param userNo
   * @param model
   * @return
   */
  @GetMapping("/orders/{userNo}")
  public String orders(@PathVariable("userNo") Long userNo, Model model) {
    model.addAttribute("user", userService.findByNo(userNo));
    model.addAttribute("orders", orderService.findByUserNo(userNo));
    return "orders";
  }
  
  
  
  
}
