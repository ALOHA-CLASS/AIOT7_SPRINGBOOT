package com.aloha.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import com.aloha.jpa.dao.ProductRepository;
import com.aloha.jpa.domain.Product;

// 테스트 시 기본적으로 Embedded DB(H2 등)를 사용
@DataJpaTest 
// 테스트 DB를 Embedded DB로 교체하지 않고, 설정된 MySQL DB를 그대로 사용
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)    // 기본적으로 테스트 후 DB 는 롤백되는데, false 하면 롤백 안되고 실제로 저장됨.
public class ProductRepositoryTest {

  @Autowired 
  private ProductRepository productRepository;

  // 상품 샘플 데이터
  private static final String[] PRODUCT_NAMES = {
    "생삼겹살", "특목살", "생갈비", "갈매기살", "항정살"
  };

  private static final int[] PRODUCT_PRICES = {
    15000, 15000, 15000, 15000, 16000
  };

  // 테스트 메소드 만들기
  @Test 
  void 상품5개저장조회() {
    for (int i = 0; i < PRODUCT_NAMES.length; i++) {
      Product product = new Product();
      product.setName(PRODUCT_NAMES[i]);
      product.setPrice(PRODUCT_PRICES[i]);
      productRepository.save(product);    // 저장 (INSERT)
    }

    // 상품 전체 목록 조회
    List<Product> products = productRepository.findAll();

    // 상품 5개가 등록되었는지 검증
    assertThat(products).hasSize(5);

    // "생갈비" 상품을 찾아서, 상품명, 가격 검증
    Product selected = products.stream()
                               .filter(p -> p.getName().equals("생갈비"))
                               .findFirst()
                               .orElse(null);
    
    // 이름과 가격이 등록한 값이랑 같은지 검증
    assertThat(selected).isNotNull(); // NULL 이 아닌지 검증
    assertThat(selected.getName()).isEqualTo("생갈비");   // 상품명:생갈비 검증
    assertThat(selected.getPrice()).isEqualTo(15000);     // 가격:15000 검증

  }


  
}
