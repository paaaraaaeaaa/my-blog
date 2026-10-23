package com.wanted.servlet.h_cookie_session;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 쿠키에 담을 사용자의 입력값 추출
        String cookieValue = req.getParameter("cookieValue");

        // 쿠키 객체 생성
        Cookie cookie = new Cookie("cookieValue",cookieValue);
        cookie.setMaxAge(60 * 60); // 만료 1 시간 설정

        // 브라우저에게 보내줄 쿠기 add
        resp.addCookie(cookie);

        // 방금 add한 쿠키는 다음 요청부터 req에 담겨 오므로 redirect 로 GET 재요청
        resp.sendRedirect(req.getContextPath() + "/cookie");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 해당 doGet() 메서드는 요청 후 응답 시 사용되는 메서드
//        resp.setContentType("text/html; charset=UTF-8");

        String savedValue = "없음!";

        Cookie[] cookies = req.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies ) {
                if ("cookieValue".equals(cookie.getName())) {
                    savedValue = cookie.getValue();
                    break;
                }
            }
        }

        // 쿠키에 있는 값을 화면에 보여주기 위해 넣어주기
        req.setAttribute("savedValue",savedValue);

        RequestDispatcher rd = req.getRequestDispatcher(
                "/h_cookie_session/cookie-result.jsp"
        );
        rd.forward(req, resp);
    }
}