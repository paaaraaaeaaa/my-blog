package com.wanted.servlet.i_filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/* comment.
*   해당 필터는 모든 요청에 동작하는 Filter
* */
@WebFilter("/*")
public class PerformanceFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Performance 필터 생성됨...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        long startTime = System.currentTimeMillis();

        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("[요청 된 URL] : " + req.getRequestURI());

        // 다음 서블릿 OR 필터 호출
        chain.doFilter(request,response);

        long endTime = System.currentTimeMillis();

        System.out.println("[" + req.getRequestURI() + " 의 최종 수행 시간] : " + (endTime - startTime) + "(ms)");

    }

    @Override
    public void destroy() {
        System.out.println("Performance 필터 파괴됨...");
    }

}
