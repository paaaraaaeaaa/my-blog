<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 26. 10. 23.
  Time: 오후 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
    <%
        // JSP 파일은 HTML 처럼 보이지만, 실제로는 Java 로 이루어진 Servlet 이다.
        // 태그는 내부에서 Java 소스코드를 입력할 수 있다.
        String user = (String) session.getAttribute("loggedInUser");
    %>
    <h3><%= user %></h3>
  </body>
</html>
