package com.aloha.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aloha.crud.controller.OrderController;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor 
public class CrudApplication implements CommandLineRunner {

	// lombok 활용 ⭐ 의존성 자동 주입 - 생성자 주입
	private final OrderController orderController;
	
	// ⭐ 의존성 자동 주입 - 필드 주입
	// @Autowired 
	// OrderController orderController;

	// @Autowired  // ⭐ 생성자 주입 시, @Autowired 생략 가능
	// public CrudApplication(OrderController orderController) {
	// 	this.orderController = orderController;
	// }

	// 프로그램 시작!
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
		// 1. 스프링 컨테이너 생성
		// 2. 컴포넌트 스캔 & 빈 등록
		// 3. 내장 서버 구동
		// --> run 메소드 호출
	}

	// 스프링 빈을 초기화한 뒤, 실행되는 run() 메소드를 오버라이딩
	@Override
	public void run(String... args) throws Exception {
		orderController.start();
	}

}
