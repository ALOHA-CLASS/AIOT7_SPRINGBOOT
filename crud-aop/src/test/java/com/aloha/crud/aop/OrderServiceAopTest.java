package com.aloha.crud.aop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.aloha.crud.domain.Orders;
import com.aloha.crud.service.OrderService;

/**
 * OrderService 호출을 통해 LoggingAspect / ExceptionAspect 가
 * 실제로 동작하는지 콘솔 입력(Scanner) 없이 확인하기 위한 테스트.
 */
@SpringBootTest
@ActiveProfiles("test") // CrudApplication의 콘솔 메뉴(run()) 실행을 건너뛰기 위함
class OrderServiceAopTest {

    @Autowired
    private OrderService orderService;

    /**
     * 정상 반환 케이스
     * → @Before, @After, @Around, @AfterReturning 로그가 출력되어야 한다.
     * (유효성 검증에서 걸려 DB까지 가지 않고 정상 반환되므로 실행 환경에 관계없이 항상 재현 가능)
     */
    @Test
    void 정상_실행시_로깅_어드바이스_확인() {
        Orders invalid = new Orders();
        invalid.setOrderName(""); // 주문명이 비어있어 검증 실패 → null 반환(정상 리턴, 예외 아님)

        Orders result = orderService.insert(invalid);

        assertThat(result).isNull();
    }

    /**
     * 예외 발생 케이스
     * → @AfterThrowing, ExceptionAspect, @Around 의 catch 블록 로그가 출력되어야 한다.
     * no 가 null 이면 DB 연결 여부와 무관하게 NPE가 발생하고,
     * OrderRepository가 이를 다시 던지도록 수정했기 때문에 항상 재현 가능하다.
     */
    @Test
    void 예외_발생시_예외_어드바이스_확인() {
        assertThatThrownBy(() -> orderService.select(null))
            .isInstanceOf(RuntimeException.class);
    }
}
