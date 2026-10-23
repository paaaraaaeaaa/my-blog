package com.wanted.servlet.f_forward;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/forward")
public class RequestInfoServlet extends HttpServlet {

    /* comment. 해당 서블릿의 역할
    *   1. 사용자의 로그인 요청을 전달 받는다.
    *   2. 요청 객체에서 값을 꺼내어 로그인 처리를 진행한다.
    *   --
    *   하지 않을 것.
    *   1. 로그인 처리 완료 후 응답 HTML return
    *  */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("사용자의 로그인 요청을 처리하는 서블릿 호출됨...");

        String id = req.getParameter("userId");
        String pwd = req.getParameter("password");

        System.out.println("id = " + id);
        System.out.println("pwd = " + pwd);

        // 전달 받은 ID , PWD 를 Service -> DAO -> Mysql 로 전달해서
        // 로그인을 진행하는 서비스 메서드 호출
        // memberService.login(id, pwd);

        System.out.println("로그인 성공!!!");

        // 로그인 성공 후 해야 하는 것.
        // 사용자에게 "조평훈" 님 환영합니다! 라는 HTML 응답.

        /* comment.
        *   해당 서블릿은 사용자의 요청이 들어오면
        *   비즈니스 로직을 처리하는 용도의 책임만
        *   갖게 한 후, 응답은 응답의 책임을 수행하는
        *   서블릿에게 위임하겠다.
        *  */

        // 전달해줄 값 집어넣은 setter 메서드
        req.setAttribute("userId", id);

        RequestDispatcher rd = req.getRequestDispatcher("/print");
        rd.forward(req, resp);

        /* comment. 중요!
        *   1. forward 방식은 서로 다른 서블릿들이
        *   req,resp 를 공유한다.
        *   따라서 요청을 위임 받는 서블릿에게도
        *   정보를 공유할 수 있게 된다.
        *   2. forward 를 위임 받은 서블릿은
        *   client 에게 존재가 노출되지 않는다.
        *   == url 의 변화가 없다.
        *   3. forward 방식의 특징은 새로고침 시
        *   동일한 요청을 반복해서 DB 에 insert, update,
        *   delete 하는 행위가 발생할 수 있다.
        *   따라서 조회 시(select)에는 forward 방식으로
        *   진행하지만, DML 구문은 forward 방식으로 구현하면
        *   큰일난다.
        *  */

    }
}
