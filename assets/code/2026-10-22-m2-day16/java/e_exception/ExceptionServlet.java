package com.wanted.servlet.e_exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/showerrorpage")
public class ExceptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("showerrorpage 서블릿 호출됨...");

        /* comment.
        *   해당 서블릿은 web.xml 파일에 정의 된
        *   404,500 에러 코드 발생 시 location 처리 된
        *   서블릿이다.
        *   해당 개념은 forward 의 개념으로 1개의 서블릿이
        *   책임이 많아질 때, 다른 서블릿을 만들어서
        *   마치 요청을 받은 서블릿이 응답까지 하는 것처럼
        *   보여주는 것이다.
        *  */

        String forwardUrl = req.getAttribute("jakarta.servlet.forward.request_uri").toString();
        System.out.println("forwardUrl = " + forwardUrl);

        // 사용자에게 html 로 응답 / 응답 시 에러코드, 메세지 전달
        int statusCode = (int) req.getAttribute("jakarta.servlet.error.status_code");
        String msg = req.getAttribute("jakarta.servlet.error.message").toString();

        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("<!doctype html>\n")
                .append("<html>\n")
                .append("<head>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<h1>")
                .append(statusCode)
                .append(" - ")
                .append(msg)
                .append("</h1>")
                .append("</body>\n")
                .append("</html>\n");

        // 해당 코드는 응답시에 항상 해주어야
        // 한글이 깨지지 않게 된다.
        // == 계속 반복되는 코드
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(responseBuilder);
        out.flush();
        out.close();


    }
}
