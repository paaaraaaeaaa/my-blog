package com.wanted.servlet.g_redirect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {

    /* comment.
    *   redirect 방식은 재요청 시 URL 을
    *   resp.sendRedirect("재요청경로")
    *   강제로 "재요청경로" 로 변경한다.
    *   == URL 변경은 곧 a 태그를 누르는 것과
    *   마찬가지의 효과를 가지기 때문에 get 방식이다.
    *  */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("/redirect 요청됨...");

        StringBuilder responseText = new StringBuilder();
        responseText.append("<!doctype html>\n")
                .append("<html>\n")
                .append("<head>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<h3 align=\"center\">")
                .append("리다이랙트 성공!!!!!!!!!!!!!!</h3>")
                .append("</body>\n")
                .append("</html>\n");

//        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter out = resp.getWriter();
        out.print(responseText.toString());
        out.flush();
        out.close();

        /* comment.
        *   Redirect 를 하게 되면, URL 이 강제로 재정의 된다.
        *   따라서 새로고침 시 재정의 된 페이지에 대한 요청만
        *   반복된다.
        *   즉, 이전 요청에 포함 된 정보는 더이상 남아있지 않고,
        *   DML 구문 실행 시 반복 실행이 안된다.
        *   ---
        *   Redirect 는 이전 요청을 아예 모른다가 장점이자 단점이 된다.
        *   단점을 극복하기 위해 나오는 개념이 Cookie / Session 이다.
        *   Cookie / Session 은 Redirect 를 하더라도
        *   정보를 공유할 수 있게 해준다.
        *  */
        
    }
}
