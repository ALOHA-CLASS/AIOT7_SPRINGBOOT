package com.aloha.crud.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ExceptionAspect {

    /**
     * ============================================================
     * 예외 처리 AOP
     * ============================================================
     *
     * Service에서 예외가 발생하면
     * 이 메서드가 자동으로 실행된다.
     *
     * @AfterThrowing
     * → 예외가 발생한 경우에만 실행
     */
    @AfterThrowing(
        pointcut =
            "execution(* com.aloha.crud.service.OrderService*.*(..))",
        throwing = "exception"
    )
    public void exception(
        JoinPoint jp,
        Exception exception
    ) {

        log.error("==================================================");
        log.error("[Exception AOP] 예외 발생");
        log.error("--------------------------------------------------");

        // 예외가 발생한 객체
        log.error(
            "target : {}",
            jp.getTarget()
        );

        // 예외가 발생한 메서드
        log.error(
            "signature : {}",
            jp.getSignature()
        );

        // 전달된 파라미터
        log.error(
            "args : {}",
            jp.getArgs()
        );

        // 예외 클래스
        log.error(
            "exception : {}",
            exception.getClass().getSimpleName()
        );

        // 예외 메시지
        log.error(
            "message : {}",
            exception.getMessage()
        );

        // 예외 전체 StackTrace
        log.error(
            "예외 상세 정보",
            exception
        );

        log.error("==================================================");
    }
}