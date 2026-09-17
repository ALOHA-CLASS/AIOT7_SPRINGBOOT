-- Active: 1788828726528@@127.0.0.1@3306@criteria
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE product;

-- ------------------------------------------------------------
-- product (no: 1~8)
-- ------------------------------------------------------------
INSERT INTO product (id, name, price, stock) VALUES
(UUID(), '무선 마우스', 15000, 100),
(UUID(), '기계식 키보드', 89000, 50),
(UUID(), '27인치 모니터', 259000, 30),
(UUID(), '노트북 거치대', 32000, 80),
(UUID(), 'USB-C 허브', 45000, 60),
(UUID(), '웹캠', 68000, 40),
(UUID(), '헤드셋', 55000, 70),
(UUID(), '게이밍 의자', 189000, 20);

SET FOREIGN_KEY_CHECKS = 1;
