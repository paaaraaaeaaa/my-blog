package com.wanted.servlet.d_response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/response")
public class ResponseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* comment. Servlet 의 역할
        *   1. 요청 전달 받기
        *   - Http Method GET/POST 요청에 따라서
        *   - parameter 로 전달 받은 데이터를 꺼내올 수 있다.
        *   2. MVC 패턴 중 Controller 의 역할을 수행
        *   - 전달 받은 데이터를 Service 계층에 전달하는 역할
        *   3. 응답 전달 하기
        *   - Response 객체를 활용해서 사용자에게 응답
        *  */

        StringBuilder response = new StringBuilder();
        response.append("<!doctype html>\n")
                .append("<html>\n")
                .append("<body>\n")
                .append("<h1>Servlet 의 응답 결과!</h1>")
                .append("</body>\n")
                .append("</html>\n");

        System.out.println("response type : " + resp.getContentType());
        // 서버에서 응답하는 데이터의 형태는 html 형태이다.
        resp.setContentType("text/html; charset=UTF-8");


        // 브라우저 화면에 노출 시키는 객체
        PrintWriter out = resp.getWriter();
        out.print(response);
        out.flush();
        out.close();


    }
}
