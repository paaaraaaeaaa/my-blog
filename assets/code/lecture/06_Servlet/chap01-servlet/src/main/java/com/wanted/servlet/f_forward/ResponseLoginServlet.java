package com.wanted.servlet.f_forward;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/print")
public class ResponseLoginServlet extends HttpServlet {

    /* comment.
    *   해당 서블릿은 RequestInfoServlet 으로부터
    *   응답에 대한 처리를 위임 받는 Servlet 이다.
    *   - req, resp 객체를 RequestInfoServlet 부터 전달 받는다.
    *   ----
    *   forward 를 요청한 서블릿에서 처리하는 메서드가 get 이면
    *   forward 를 받은 서블릿도 get 메서드를 오버라이딩 한다.
    *  */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("/print 서블릿 호출됨...");

        // 해당 doPost() 는 html 을 만들어서
        // ~~ 님 환영합니다! 라는 응답을 진행 할 것이다.

        // 이전 서블릿이 전달해준 id 꺼내기
        String id = req.getAttribute("userId").toString();

        StringBuilder responseText = new StringBuilder();
        responseText.append("<!doctype html>\n")
                .append("<html>\n")
                .append("<head>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<h3 align=\"center\">")
                .append(id)
                .append("님 환영합니다.</h3>")
                .append("</body>\n")
                .append("</html>\n");

//        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter out = resp.getWriter();

        out.print(responseText.toString());

        out.flush();
        out.close();

    }
}
