package com.wanted.servlet.c_parameter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/formdata")
public class FormDataServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost() 호출됨...");
        String name = req.getParameter("name");
        System.out.println("name = " + name);

        /* comment.
        *   GET/POST 는 HTTP 요청에서 가장 많이 쓰이는 방식이다.
        *   이 둘의 가장 큰 차이는 URL 로 데이터를 전달하는 지에 대한 여부이다.
        *   - GET 요청
        *   URL 에 데이터가 노출이 되며, 데이터의 크기도 한정된다.
        *   URL 데이터가 노출되어 보안상 취약할 수 있다.
        *   또한 대략 200자 내의 데이터만 전달할 수 있기 때문에 게시글 등을 Insert 하는 것에는 적합하지 않다.
        *   다만, GET 은 속도가 빠르기 때문에 조회 시 많이 사용된다.
        *   - POST 요청
        *   데이터가 URL 에 노출되지 않고 Payload(Body) 불리는 영역에 데이터를 담아서 Server 에 전달한다.
        *   전달할 수 있는 데이터의 크기가 압도적으로 크다.
        *   다만, 속도가 느리기 때문에 단순 조회 등에는 GET 을 사용한다.
        * */
    }
}
