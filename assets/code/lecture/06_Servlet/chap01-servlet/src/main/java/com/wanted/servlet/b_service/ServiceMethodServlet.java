package com.wanted.servlet.b_service;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/request-service")
public class ServiceMethodServlet extends HttpServlet {

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        /* comment.
//        *   a 태그의 href 속성은 화면의 URL 을 변경하는 속성이다.
//        *   URL 이 변경되는 방식은 GET 방식의 요청에 해당한다.
//        *  */
//        System.out.println("req : " + req);
//        String httpMethod = req.getMethod();
//        System.out.println("httpMethod = " + httpMethod);
//
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET 요청을 처리할 메소드 호출됨...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST 요청을 처리할 메소드 호출됨...");
    }
}
