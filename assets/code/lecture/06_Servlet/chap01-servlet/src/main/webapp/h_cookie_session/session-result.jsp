<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 26. 10. 23.
  Time: 오전 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
        <h1>세션 확인!</h1>
        <p>로그인 된 사용자 ID : ${loggedInUser}</p>
        <p>PWD : ${pwd}</p>
        <a href = "/expire">세션 만료 시키기</a>
  </body>
</html>
