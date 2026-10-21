package com.wanted.servlet.c_parameter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/querystring")
public class QueryStringServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // http://localhost:8080/querystring?name=홍길동&age=23&birthday=2000-01-01&gender=F&national=ko&hobbies=movie

        System.out.println("doGet() 호출됨...");

        String name = req.getParameter("name");
        System.out.println("name = " + name);

        /* comment.
        *   URL 로 전달되는 데이터는 모두 String 타입이다.
        *   따라서 원하는 자료형으로 담기 위해서는 파싱이 필요하다.
        * */
        int age = Integer.parseInt(req.getParameter("age"));
        System.out.println("age = " + age);


    }
}
