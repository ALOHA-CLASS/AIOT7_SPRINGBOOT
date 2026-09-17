package com.aloha.criteria_api.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.aloha.criteria_api.domain.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * ProductRepository 의 커스텀 구현클래스
 * 이름 규칙(Repository 인터페이스명 + Impl) 형태로 쓰면
 * Spring Data JPA가 자동으로 인식한다.
 */
@Repository 
public class ProductRepositoryImpl implements ProductRepositoryCustom {

  @PersistenceContext         // 의존성 주입
  private EntityManager em;   // JPA 실행에 사용되는 관리객체

  @Override
  public List<Product> findByContaining(String keyword) {
    CriteriaBuilder cb = em.getCriteriaBuilder(); // Criteria 빌더 객체
    CriteriaQuery<Product> cq = cb.createQuery(Product.class);  // Product 타입을 반환하는 쿼리 생성
    Root<Product> root = cq.from(Product.class);                // Product 엔터티를 FROM 절로 지정
    // (쿼리) FROM Proudct

    // * 쿼리 생성
    cq.select(root)                                           // SELECT *
      .where(cb.like(root.get("name"), "%" + keyword + "%"))  // WHERE name LIKE '%????%'
      .orderBy(cb.desc(root.get("no")));                      // ORDER BY no DESC

    // createQuery(cq) : 쿼리 실행
    // getResultList() : 결과 리스트 반환 -> List<Product>
    return em.createQuery(cq).getResultList();  
  }

  @Override
  public List<Product> findByPriceBetween(int minPrice, int maxPrice) {
    CriteriaBuilder cb = em.getCriteriaBuilder(); // Criteria 빌더 객체
    CriteriaQuery<Product> cq = cb.createQuery(Product.class);  // Product 타입을 반환하는 쿼리 생성
    Root<Product> root = cq.from(Product.class);                // Product 엔터티를 FROM 절로 지정
    // (쿼리) FROM Proudct

    // * 쿼리 생성
    cq.select(root)                                             // SELECT *
      .where(cb.between(root.get("price"), minPrice, maxPrice)) // WHERE price BETWEEN ? AND ?
      .orderBy(cb.asc(root.get("price")));                      // ORDER BY price ASC

    return em.createQuery(cq).getResultList();
  }

  @Override
  public List<Product> findAllOrderByPriceDesc() {
    CriteriaBuilder cb = em.getCriteriaBuilder(); // Criteria 빌더 객체
    CriteriaQuery<Product> cq = cb.createQuery(Product.class);  // Product 타입을 반환하는 쿼리 생성
    Root<Product> root = cq.from(Product.class);                // Product 엔터티를 FROM 절로 지정
    // (쿼리) FROM Proudct

    cq.select(root)                                 // SELECT
      .orderBy(cb.desc(root.get("price")));         // ORDER BY price DESC

    return em.createQuery(cq).getResultList();    
  }

  @Override
  public List<Product> searchByCriteria(String name, Integer minPrice, Integer maxPrice, Integer minStock) {
    CriteriaBuilder cb = em.getCriteriaBuilder(); // Criteria 빌더 객체
    CriteriaQuery<Product> cq = cb.createQuery(Product.class);  // Product 타입을 반환하는 쿼리 생성
    Root<Product> root = cq.from(Product.class);                // Product 엔터티를 FROM 절로 지정
    // (쿼리) FROM Proudct

    // ⭐ Predicate 를 사용해서, 조건을 리스트에 추가하여 동적 쿼리를 만드는 방식
    List<Predicate> predicates = new ArrayList<>();   // WHERE 조건1, 조건2, ...

    // name 이 존재할 때만 LIKE 조건 추가
    if( name != null && !name.isBlank() ) 
      predicates.add(cb.like(root.get("name"), "%" + name + "%"));

    if(minPrice != null)  // ge : greater & equal : 크거나 같다 : ~이상 : price >= ?
      predicates.add(cb.ge(root.get("price"), minPrice)); 
      
    if(maxPrice != null)  // le : less & equal : 작거나 같다 : ~이하 : price <= ?
      predicates.add(cb.le(root.get("price"), maxPrice)); 
      
    if(minStock != null)  // ge : greater & equal : 크거나 같다 : ~이상 : stock >= ?
      predicates.add(cb.ge(root.get("stock"), minStock)); 

    // 쿼리 생성
    cq.select(root)                                   // SELECT *
      .where(predicates.toArray(new Predicate[0]))    // WHERE 조건1 AND 조건2 AND ...
      .orderBy(cb.desc(root.get("no")));              // ORDER BY no DESC
    
    return em.createQuery(cq).getResultList();
  }
  
}
