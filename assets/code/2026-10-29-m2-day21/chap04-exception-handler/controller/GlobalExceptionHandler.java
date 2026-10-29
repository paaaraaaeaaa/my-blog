package com.wanted.exceptionhandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/* comment.
*   Advice 는 AOP 챕터에서 반복적으로 동작해야 하는 코드를 뭉쳐둔 메서드를 지칭하는 단어였다.
*   @ControllerAdvice 공통으로 발생할 수 있는 예외 처리 핸들러를 묶어둔 클래스를 지칭한다.
* */
@ControllerAdvice
public class GlobalExceptionHandler {

    /* comment.
    *   예외처리 핸들러는 우선순위가 존재한다.
    *   같은 클래스 내부에 있는 핸들러 > 전역 핸들러
    * */
    @ExceptionHandler(NullPointerException.class)
    public String nullHandler(Model model, NullPointerException e) {
        // 블로그에 질문글로 넣을 것. 전역/지역 변수? 처리? 의 차이점이나 더 크게 작용하는 것들 설명 바람.
        System.out.println("전역 예외처리 핸들러 동작!!");
        model.addAttribute("key", e.getMessage());

        return "error/nullPointer";
    }

    @ExceptionHandler(Exception.class)
    public String defaultException(Model model, Exception e) {

        model.addAttribute("class", e.getClass());
        model.addAttribute("message", e.getMessage());

        return "error/default";
    }

}
