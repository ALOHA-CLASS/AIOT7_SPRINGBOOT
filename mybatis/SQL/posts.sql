-- Active: 1788828726528@@127.0.0.1@3306@aloha
-- posts (게시글) 테이블
DROP TABLE IF EXISTS `posts`;
CREATE Table `posts` (
    `no`            BIGINT          AUTO_INCREMENT PRIMARY KEY  COMMENT 'PK',
    `id`            VARCHAR(64)     UNIQUE                      COMMENT 'UK',
    `title`         VARCHAR(100)    NOT NULL                    COMMENT '제목',
    `writer`        VARCHAR(100)    NOT NULL                    COMMENT '작성자',
    `content`       TEXT            NULL                        COMMENT '내용',
    `created_at`    TIMESTAMP       DEFAULT CURRENT_TIMESTAMP   COMMENT '등록일자',
    `updated_at`    TIMESTAMP       DEFAULT CURRENT_TIMESTAMP   
                                            ON UPDATE CURRENT_TIMESTAMP    
                                                                COMMENT '수정일자'
) COMMENT '게시글';

INSERT INTO `posts` (`id`, `title`, `writer`, `content`) VALUES
('post-001', '안녕하세요. 첫 번째 게시글입니다.', '홍길동', '게시판에 작성한 첫 번째 게시글입니다.'),
('post-002', 'Spring Boot 공부하고 있습니다.', '김철수', 'Spring Boot를 처음 공부하고 있습니다. 생각보다 재미있네요.'),
('post-003', 'MySQL 테이블 설계 질문드립니다.', '이영희', '게시판 테이블을 설계할 때 어떤 부분을 주의해야 하나요?'),
('post-004', '오늘 날씨가 정말 좋네요.', '박민수', '날씨가 좋아서 산책하기 좋은 하루인 것 같습니다.'),
('post-005', 'JPA와 MyBatis 어떤 것을 사용할까요?', '최지훈', '프로젝트를 시작하면서 JPA와 MyBatis 중 고민하고 있습니다.'),
('post-006', '개발 공부 방법 추천해주세요.', '정수빈', '개발을 처음 시작했는데 어떤 순서로 공부하면 좋을까요?'),
('post-007', '게시판 프로젝트를 만들고 있습니다.', '강민지', 'Spring Boot를 이용해서 간단한 게시판 프로젝트를 만들고 있습니다.'),
('post-008', 'REST API에 대해 궁금합니다.', '윤서준', 'REST API를 설계할 때 가장 중요하게 생각해야 할 부분이 무엇인가요?'),
('post-009', 'HTML과 CSS 공부 후기', '한지민', 'HTML과 CSS를 공부하면서 간단한 웹페이지를 만들어 보았습니다.'),
('post-010', 'Java 기초 문법 질문입니다.', '오준혁', '객체지향 프로그래밍을 공부하다가 궁금한 점이 생겼습니다.'),
('post-011', 'Git 사용법 공유합니다.', '서예진', 'Git을 처음 사용하는 분들에게 도움이 될 만한 기본 명령어를 정리했습니다.'),
('post-012', '팀 프로젝트 시작했습니다.', '김도윤', '오늘부터 팀원들과 함께 새로운 프로젝트를 시작하게 되었습니다.'),
('post-013', '회원가입 기능 구현 완료!', '이준호', 'Spring Security를 이용한 회원가입 기능을 구현했습니다.'),
('post-014', '로그인 기능에서 오류가 발생합니다.', '박서연', '로그인 요청을 보낼 때 인증 과정에서 오류가 발생하고 있습니다.'),
('post-015', 'Thymeleaf 사용해 보신 분 계신가요?', '최현우', 'Spring Boot 프로젝트에서 Thymeleaf를 사용하고 있는데 생각보다 편리하네요.'),
('post-016', '오늘 배운 내용을 정리했습니다.', '김하늘', '오늘 수업에서 배운 내용을 복습하면서 간단하게 정리해 보았습니다.'),
('post-017', '게시판 페이징 구현 완료했습니다.', '이수민', '게시글 목록에 페이징 기능을 추가했습니다. 페이지 이동도 정상적으로 동작합니다.'),
('post-018', '파일 업로드 기능 질문입니다.', '박지훈', '게시글에 이미지나 파일을 첨부하는 기능을 구현하려고 합니다.'),
('post-019', '프로젝트 발표를 준비하고 있습니다.', '장서윤', '팀 프로젝트 결과물을 정리하고 발표 자료를 준비하고 있습니다.'),
('post-020', '개발 공부하시는 분들 모두 화이팅!', '최민재', '꾸준히 공부하다 보면 좋은 결과가 있을 거라고 생각합니다. 모두 화이팅입니다!');