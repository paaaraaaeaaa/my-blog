package com.wanted.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class GlobalTestController {


    /* comment.
    *   ExceptionTestController 에서 작성해둔 null-pointer 예외처리 핸들러가 동작하지 않는다.
    *   이유는 다른 클래스 내부에 있는 메서드는 알 수 없기 때문이다.
    * */

    @GetMapping("global-nullpointer")
    public String globalNull() {
        // 의도적 nullPointerException 발생
        String str = null;
        System.out.println(str.toLowerCase(Locale.ROOT));

        return "/";
    }

    @GetMapping("global-default")
    public String anyException() {
        int[] iarr = new int[0];
        System.out.println(iarr[1]);

        return "/";
    }

}
