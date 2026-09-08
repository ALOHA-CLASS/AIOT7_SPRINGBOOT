package com.aloha.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.aloha.crud.controller.OrderController;
import com.aloha.crud.dao.JDBConnection;
import com.aloha.crud.dao.OrderRepository;
import com.aloha.crud.service.OrderService;
import com.aloha.crud.service.OrderServiceImpl;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(CrudApplication.class, args);
		// DB 접속 테스트
		// JDBConnection jdbc = context.getBean(JDBConnection.class);
		// jdbc.getConnection();
		// OrderRepository orderRepository = context.getBean(OrderRepository.class);

		// CrudApplication --의존--> OrderRepository
		OrderRepository orderRepository = new OrderRepository();

		// orderRepository ---의존성 주입---> OrderService
		OrderService orderService = new OrderServiceImpl(orderRepository);

		// orderService ---의존성 주입---> OrderController
		OrderController orderController = new OrderController(orderService);

		orderController.start();
	}

}
