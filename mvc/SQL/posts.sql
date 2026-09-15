-- Active: 1788828726528@@127.0.0.1@3306@aloha
TRUNCATE Table posts;

INSERT INTO posts (id, title, writer, content)
VALUES 
  ( UUID(), '제목1', '작성자1', '내용1'),
  ( UUID(), '제목2', '작성자2', '내용2'),
  ( UUID(), '제목3', '작성자3', '내용3'),
  ( UUID(), '제목4', '작성자4', '내용4'),
  ( UUID(), '제목5', '작성자5', '내용5')
;
