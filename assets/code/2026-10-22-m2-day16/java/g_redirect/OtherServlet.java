package com.wanted.servlet.g_redirect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/otherservlet")
public class OtherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("/otherservlet 요청 됨...");

        // 회원가입 로직 동작..
        // memberService.signUp();
        System.out.println("회원정보 DB 에 Insert 완료!");

        /* comment.
        *   Redirect 는 요청을 수신하고
        *   응답 시에 어디로 재요청을 보내야 된다고
        *   응답을 하게 된다.
        *  */
        resp.sendRedirect("/redirect");
    }
}
