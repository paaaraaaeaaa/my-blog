package com.wanted.servlet.h_cookie_session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/expire")
public class ExpireServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // 세션이 존재한다면
        if (session != null) {
            // 세션 무효화 메서드
            session.invalidate();
        }

        // 세션 무효화 후 /session 으로 리다이렉트
        resp.sendRedirect("/session");

    }
}
