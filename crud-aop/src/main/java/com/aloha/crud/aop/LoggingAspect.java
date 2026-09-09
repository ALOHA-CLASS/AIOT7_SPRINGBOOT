package com.aloha.crud.aop;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAspectJAutoProxy   // ⭐ AOP 기능 활성화
@Component                // ⭐ 스프링 빈으로 등록
@Aspect                   // ⭐ AOP 클래스라는 의미
public class LoggingAspect {

    /*
     * ============================================================
     * AOP Advice 종류
     * ============================================================
     *
     * @Before
     *  → 타겟 메서드 실행 전에 실행
     *
     * @After
     *  → 타겟 메서드 실행이 끝난 후 실행
     *  → 정상 실행 / 예외 발생 여부와 관계없이 실행
     *
     * @AfterReturning
     *  → 타겟 메서드가 정상적으로 실행되고 반환될 때 실행
     *
     * @AfterThrowing
     *  → 타겟 메서드에서 예외가 발생했을 때 실행
     *
     * @Around
     *  → 타겟 메서드 실행 전/후를 모두 제어
     *  → jp.proceed()를 호출해야 실제 메서드가 실행됨
     */


    /*
     * ============================================================
     * Pointcut
     * ============================================================
     *
     * execution(
     *      반환타입
     *      패키지.클래스.메서드
     *      (파라미터)
     * )
     *
     * 예)
     *
     * execution(
     *      * com.aloha.crud.service.OrderService*.*(..)
     * )
     *
     * 의미
     *
     * com.aloha.crud.service
     *      ↓
     * service 패키지
     *
     * OrderService*
     *      ↓
     * OrderService로 시작하는 클래스
     *
     * *
     *      ↓
     * 모든 메서드
     *
     * (..)
     *      ↓
     * 파라미터 개수와 타입 상관없이 모두
     */


    /**
     * ⭐ 메서드 실행 전에 로그
     */
    @Before(
        "execution(* com.aloha.crud.service.OrderService*.*(..))"
    )
    public void before(JoinPoint jp) {

        log.info("==================================================");
        log.info("[@Before] 메서드 실행 전");
        log.info("--------------------------------------------------");

        // 타겟 객체
        log.info("target : {}", jp.getTarget());

        // 메서드 정보
        log.info("signature : {}", jp.getSignature());

        // 전달된 인자값
        log.info("args : {}", Arrays.toString(jp.getArgs()));

        // 현재 시간
        log.info("현재 시간 : {}", LocalDateTime.now());

        // 파라미터 이름과 값 출력
        printParam(jp);

        log.info("==================================================");
    }


    /**
     * ⭐ 메서드 실행이 끝난 후 로그
     *
     * 정상 실행되든
     * 예외가 발생하든
     * 실행됨
     */
    @After(
        "execution(* com.aloha.crud.service.OrderService*.*(..))"
    )
    public void after(JoinPoint jp) {

        log.info("==================================================");
        log.info("[@After] 메서드 실행 종료");
        log.info("--------------------------------------------------");

        log.info("target : {}", jp.getTarget());
        log.info("signature : {}", jp.getSignature());

        log.info("==================================================");
    }


    /**
     * ⭐ 메서드 실행 시간 측정
     * @throws Throwable 
     *
     * @Around
     *
     * 메서드 실행 전
     *      ↓
     * 실제 메서드 실행
     *      ↓
     * 메서드 실행 후
     *
     * 전체 과정을 감쌀 수 있음
     */
    @Around(
        "execution(* com.aloha.crud.service.OrderService*.*(..))"
    )
    public Object around(ProceedingJoinPoint jp) throws Throwable {

        log.info("==================================================");
        log.info("[@Around] 메서드 실행");

        // 실행 시작 시간
        long start = System.currentTimeMillis();

        Object result = null;

        try {

            /*
             * ⭐ 실제 타겟 메서드 실행
             *
             * proceed()를 호출해야
             * OrderService의 실제 메서드가 실행된다.
             */
            result = jp.proceed();

            // 정상적으로 반환된 결과
            log.info("반환값 : {}", result);

        } catch (Throwable e) {

            /*
             * 타겟 메서드에서 발생한 예외
             */
            log.error("예외 발생 : {}", e.getMessage());

            /*
             * 중요!
             *
             * 여기서 예외를 먹어버리면
             * 호출한 쪽에서는 예외가 발생하지 않은 것으로
             * 처리될 수 있다.
             *
             * 따라서 예외를 다시 던져준다.
             */
            throw e;

        } finally {

            // 실행 종료 시간
            long end = System.currentTimeMillis();

            // 실행 시간
            long elapsed = end - start;

            log.info("실행 시간 : {} ms", elapsed);

            log.info("==================================================");
        }

        return result;
    }


    /**
     * ⭐ 정상적으로 메서드가 종료되었을 때
     *
     * returning = "result"
     *
     * → 실제 메서드의 반환값을
     *    result 변수에 전달
     */
    @AfterReturning(
        pointcut =
            "execution(* com.aloha.crud.service.OrderService*.*(..))",
        returning = "result"
    )
    public void afterReturning(
        JoinPoint jp,
        Object result
    ) {

        log.info("==================================================");
        log.info("[@AfterReturning] 정상 실행");
        log.info("--------------------------------------------------");

        log.info("target : {}", jp.getTarget());
        log.info("signature : {}", jp.getSignature());

        // 반환값 출력
        log.info("반환값 : {}", result);

        log.info("==================================================");
    }


    /**
     * ⭐ 메서드 실행 중 예외가 발생했을 때
     *
     * throwing = "exception"
     *
     * → 발생한 예외를
     *    exception 변수로 전달
     */
    @AfterThrowing(
        pointcut =
            "execution(* com.aloha.crud.service.OrderService*.*(..))",
        throwing = "exception"
    )
    public void afterThrowing(
        JoinPoint jp,
        Exception exception
    ) {

        log.error("==================================================");
        log.error("[@AfterThrowing] 예외 발생");
        log.error("--------------------------------------------------");

        log.error("target : {}", jp.getTarget());
        log.error("signature : {}", jp.getSignature());

        // 예외 메시지
        log.error("예외 메시지 : {}", exception.getMessage());

        // 예외 종류
        log.error(
            "예외 클래스 : {}",
            exception.getClass().getSimpleName()
        );

        // 예외 전체 StackTrace 출력
        log.error("예외 내용", exception);

        log.error("==================================================");
    }


    /**
     * ============================================================
     * 파라미터 출력
     * ============================================================
     *
     * 예를 들어
     *
     * OrderService.select(Integer no)
     *
     * 가 실행되면
     *
     * 파라미터명 : no
     * 값 : 10
     *
     * 형태로 출력
     */
    public void printParam(JoinPoint jp) {

        /*
         * 메서드 시그니처 정보
         */
        Signature signature = jp.getSignature();

        /*
         * MethodSignature로 형변환
         *
         * MethodSignature를 사용하면
         * 메서드의 파라미터 이름을 가져올 수 있다.
         */
        MethodSignature methodSignature =(MethodSignature) signature;

        /*
         * 파라미터 이름
         */
        String[] parameterNames =
            methodSignature.getParameterNames();

        /*
         * 실제 전달된 값
         */
        Object[] args = jp.getArgs();

        /*
         * 파라미터가 존재하는 경우
         */
        if (parameterNames != null) {

            for (int i = 0; i < parameterNames.length; i++) {

                String paramName = parameterNames[i];

                Object paramValue = args[i];

                log.info(
                    "파라미터명 : {}, 값 : {}",
                    paramName,
                    paramValue
                );
            }
        }
    }
}