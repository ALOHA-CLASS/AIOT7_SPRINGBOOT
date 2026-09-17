package com.aloha.querydsl.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aloha.querydsl.domain.Product;
import com.aloha.querydsl.domain.QProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

/**
 * ProductRepository 의 커스텀 구현클래스
 * 이름 규칙(Repository 인터페이스명 + Impl) 형태로 쓰면
 * Spring Data JPA가 자동으로 인식한다.
 */
@Repository 
@RequiredArgsConstructor 
public class ProductRepositoryImpl implements ProductRepositoryCustom {

  private final JPAQueryFactory queryFactory; // QuerydslConfig 에서 등록한 빈 주입

  private static final QProduct product = QProduct.product;   // Product 의 Q타입 인스턴스

  @Override
  public List<Product> findByContaining(String keyword) {
    return queryFactory
          .selectFrom(product)                      // SELECT ... FROM product
          .where(product.name.contains(keyword))    // WHERE name LIKE '%?%'
          .orderBy(product.no.desc())               // ORDER BY no desc
          .fetch();                                 // 쿼리 실행 후 결과 리스트 반환
  }

  @Override
  public List<Product> findByPriceBetween(int minPrice, int maxPrice) {
     return queryFactory
        .selectFrom(product)
        .where(product.price.between(minPrice, maxPrice)) // price BETWEEN minPrice AND maxPrice
        .orderBy(product.price.asc()) // price 기준 오름차순 정렬
        .fetch();
  }

  @Override
  public List<Product> findAllOrderByPriceDesc() {
    return queryFactory
        .selectFrom(product)
        .orderBy(product.price.desc()) // price 기준 내림차순 정렬 (조건절 없이 전체 조회)
        .fetch();
  }

  @Override
  public List<Product> searchByCriteria(String name, Integer minPrice, Integer maxPrice, Integer minStock) {
    // 조건이 있는 항목만 BooleanBuilder 에 누적하는 동적 쿼리 방식
    BooleanBuilder builder = new BooleanBuilder(); // 최종 WHERE 절에 들어갈 조건들을 누적할 빌더

    if (name != null && !name.isBlank()) { // name 파라미터가 존재할 때만 조건 추가
      builder.and(product.name.contains(name)); // name 포함 검색 조건
    }
    if (minPrice != null) { // 최소 가격이 지정된 경우
      builder.and(product.price.goe(minPrice)); // price >= minPrice 조건
    }
    if (maxPrice != null) { // 최대 가격이 지정된 경우
      builder.and(product.price.loe(maxPrice)); // price <= maxPrice 조건
    }
    if (minStock != null) { // 최소 재고가 지정된 경우
      builder.and(product.stock.goe(minStock)); // stock >= minStock 조건
    }

    return queryFactory
        .selectFrom(product)
        .where(builder) // 누적된 조건들을 AND 로 결합하여 WHERE 절 구성
        .orderBy(product.no.desc()) // no 컬럼 기준 내림차순 정렬
        .fetch();
  }
  
}
