-- Active: 1788828726528@@127.0.0.1@3306@aloha
-- 주문 테이블
/*
  번호
  ID
  주문명
  총금액
  등록일자
  수정일자
*/
CREATE TABLE orders (
  no            INT             NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '주문PK',
  id            VARCHAR(36)     NOT NULL DEFAULT (UUID()) UNIQUE COMMENT '주문ID',
  order_name    VARCHAR(100)    NOT NULL COMMENT '주문명',
  total_amount  INT             NOT NULL DEFAULT 0 COMMENT '총금액',
  created_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  updated_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP 
                                ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일자'
);