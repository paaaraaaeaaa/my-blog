package com.wanted.servlet.i_filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/* comment.
 *   Filter 는 Http 요청이 Servlet 에 도달하기
 *   전에 동작하는 생명주기를 갖는다.
 *   @WebFilter("필터가 동작하는 경로")
 *  */
@WebFilter("/auth/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("로그인 관련 필터 초기화됨...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 자식이 가지고 있는 getSession() 메서드를 사용하기 위해
        // 클래스 형변환
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        /* comment.
         *   /auth 로 시작하는 요청이 오면 동작하는 filter
         *   해당 필터는 session 에 loggedInUser 값이 없으면
         *   null || 500-error 에러를 발생시키는 것이 아닌,
         *   로그인을 할 수 있는 페이지로 redirect 시킬 것이다.
         *  */

        // 기존 세션 유무 확인!
        HttpSession session = req.getSession(false);

        if(session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect("/h_cookie_session/cookie_session.html");
        } else {
            // else : 세션 존재하고, loggedInUser 에 값이 있는 상태!
            // == 정상이기때문에 Servlet 으로 갈 수 있게 만들어보자!!
            chain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy() {
        System.out.println("로그인 관련 필터 파괴됨...");
    }
}