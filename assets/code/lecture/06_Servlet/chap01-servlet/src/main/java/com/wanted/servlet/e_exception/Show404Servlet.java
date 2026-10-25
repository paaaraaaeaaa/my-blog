package com.wanted.servlet.e_exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/show404")
public class Show404Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("404 Servlet 호출됨...");
        // throw new Exception(); 에러 터진다는 가정

        resp.sendError(404 , "페이지가 없습니다!");

    }
}
