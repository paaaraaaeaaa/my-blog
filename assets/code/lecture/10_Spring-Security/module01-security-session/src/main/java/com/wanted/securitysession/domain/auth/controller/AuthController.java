package com.wanted.securitysession.domain.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthController {

    /* comment.
    *   [로그인 ①] 로그인 화면 보여주기 (GET)
    *   로그인 처리(POST/auth/login) 메서드는 여기에 없다.
    *   form 이 POST 로 전송되면 컨트롤러가 아닌 Security 필터가 가로채서 처리한다. ([로그인 ②])
    *  */
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    // [로그인 ⑥-2] AuthFailHandler 가 이동시킨 실패 안내 페이지
    @GetMapping("/fail")
    public ModelAndView loginFail(@RequestParam String message, ModelAndView mv) {

        mv.addObject("message", message);
        mv.setViewName("auth/fail");

        return mv;
    }
}
