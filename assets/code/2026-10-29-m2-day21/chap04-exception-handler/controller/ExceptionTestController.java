package com.wanted.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class ExceptionTestController {

    @GetMapping("nullpointer")
    public String nullPointer() {

        // 고의적으로 Error 발생
        String str = null;
        System.out.println(str.toLowerCase(Locale.ROOT));
        // 위 16번 줄이 실행되면 NullPointerException 객체가 발생한다.
        return "/";

    }

    /* comment.
    *   @ExceptionHandler(처리하고자 하는 예외 클래스)
    *   이렇게 정의해두면, 예외핸들러가 Controller 의 흐름을 가로채서 동작을 하게 된다.
    * */

    @ExceptionHandler(NullPointerException.class)
    public String nullHandler(Model model, NullPointerException e) {

        model.addAttribute("key", e.getMessage());

        return "error/nullPointer";
    }

    @GetMapping("userexception")
    public String userException() throws MemberNotFoundException {
        boolean check = true;
        if (check) {
            throw new MemberNotFoundException("아프지 말자 우리");
        }
        return "/";

    }

    @ExceptionHandler(MemberNotFoundException.class)
    public String userException(Model model, MemberNotFoundException e) {

        model.addAttribute("key", e.getMessage());

        return "error/nullPointer";
    }

}
