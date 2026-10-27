package com.wanted.aop.section02.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class PerformanceAspect {

    /* comment. @Around
    *   Advice 중 가장 강력한 어노테이션이다.
    *   Pointcut 으로 지정한 메서드의 앞/뒤에 모두 동작한다.
    *  */
    @Around("execution(* com.wanted.aop.section02.MemberService.*(..))")
    public Object performance(ProceedingJoinPoint joinPoint) {

        // 시작 시간 측정
        long startTime = System.currentTimeMillis();

        // 이 공간에 타겟 메서드를 호출해야 된다.
        // ProceedingJoinPoint : 타겟 메서드의 실행을 직접 제어할 수 있다.
        try {
            Object result = joinPoint.proceed();
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            // 종료 시간 측정
            long endTime = System.currentTimeMillis();
            String methodName = joinPoint.getSignature().getName();

            System.out.println("[Around 성능 측정] " + methodName + " 메서드 실행 시간 : " +
                    "" + (endTime - startTime) + "(ms)");
        }

    }

}
