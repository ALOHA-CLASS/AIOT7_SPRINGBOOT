package com.aloha.querydsl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * 
 * QueryDSL 이 사용할 JPAQueryFactor 를 스프링 빈으로 등록
 */
@Configuration 
public class QuerydslConfig {

  @PersistenceContext 
  private EntityManager em;

  @Bean 
  public JPAQueryFactory jpaQueryFactory() {
    return new JPAQueryFactory(em);
  }
  
}
