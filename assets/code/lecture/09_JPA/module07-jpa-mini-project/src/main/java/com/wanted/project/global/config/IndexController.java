package com.wanted.project.global.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * [IndexController] (제공 코드)
 * 역할 : / , /main 요청 시 메인 화면(main/main)을 보여준다.
 * 책임 : 화면 이동만 담당한다. 메뉴 관련 요청은 MenuController 의 책임이다.
 */
@Controller
public class IndexController {

    @GetMapping(value = {"/" , "/main"})
    public String mainPage() {
        return "main/main";
    }
}
