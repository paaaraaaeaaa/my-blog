package com.wanted.aop.section02.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    /* comment.
    *   해당 클래스는 각 메서드 마다
    *   반복되는 Log 관련 코드를 하나의 관점으로
    *   모아두고, 필요한 시점에 동작하게 정의를 해둔
    *   클래스이다.
    *   ---
    *   현재 Log 가 각 메서드 별 동작하는 시점
    *   - 메서드 호출 시
    *   - 비즈니스 로직 종료 시
    *   - 예외 발생 시
    *  */

    /* comment. AOP 관련 용어
    *   @Aspect
    *   - 해당 클래스가 AOP 의 Aspect(관점) 임을 선언한다.
    *   - 해당 클래스 내부에서 관점들을 어디(PointCut)에
    *   - 적용할 지와 무엇(Advice)을 할 지를 메서드 단위로
    *   - 묶게 된다.
    *   Advice
    *   - @Before, @After , @AfterReturning
    *   - @AfterThrowing , @Around 로 구성 되어 있다.
    *   Pointcut
    *   - 해당 메서드(Advice) 가 어디에서 실행될 지를 정의
    *   - "execution(* com.wanted.aop.section02.MemberService.*(..))"
    *   - "execution(  접근제어자 반환타입 패키지.클래스.메서드(파라미터)  )"
    *   - 클래스.*(..) : 클래스 내부의 모든 메서드를 의미
    *   - (..) : 매개변수 0개 이상
    *   - () : 매개변수 없는 메서드
    *   - (String) : 매개변수가 String 1개인 메서드
    *   - (String, ..) : 첫번째 매개변수는 String 이며, 나머진 상관 없음
    *  */

    // 타겟 메서드 동작 전 실행 될 Log 메서드
    @Before("execution(* com.wanted.aop.section02.MemberService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        // JoinPoint 란? Advice 메서드가 적용되는 지점에 대한
        // 정보를 제공하는 객체이다. Advice 메서드 매개변수 부분에
        // 작성을 한다.
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        StringBuilder argStr = new StringBuilder();
        for (Object arg : args) {
            argStr.append(arg).append(" , ");
        }

        System.out.println("[Before 로그] " + methodName + "" +
                " 메서드 실행 시작 됨, 매개변수 : " + argStr);

    }

    /* comment.
    *   @After : Pointcut 명시 된 타겟 메서드가 실행 완료 된 후
    *   실행되는 Advice
    *   타이머 종료, 종료 로그 , 자원 정리 이럴 때 사용이 된다.
    *  */
    @After("execution(* com.wanted.aop.section02.MemberService.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[After 로그] " + methodName + " 메서드 실행 완료!");
    }

    /* comment.
     *   @AfterReturning
     *   대상 메서드가 정상적으로 실행을 완료하고, 값을 반환한 후 실행되는 Advice
     *   대상 메서드의 return 값을 확인하거나, 값을 변환할 때 사용될 수 있음.
     *   returning : 메서드의 반환값을 result 라는 이름의 매개변수로 받겠다는 의미
     * */
    @AfterReturning(
            pointcut = "execution(* com.wanted.aop.section02.MemberService.*(..))",
            returning = "result"
    )
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[AfterReturn 로그] " + methodName + " 메소드 정상 종료, 종료 시 반환값 : " + result);
    }

    /* comment.
     *   @AfterThrowing : 대상 메서드 실행 중 예외가 발생했을 때 동작하는 Advice
     *   사용 예시 : 예외 관련 로그,
     *   - 예외 흐름 복구 로직 등에 사용된다. -> 할 수 있긴 하지만, 더 좋은 예외처리로
     *   하는 것이 좋다.
     *  */
    @AfterThrowing(
            pointcut = "execution(* com.wanted.aop.section02.MemberService.*(..))",
            throwing = "exception"
    )
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[AfterThrowing 로그] " + methodName + " 메소드 실행 중 예외 발생 : " + exception.getMessage());
    }




}
