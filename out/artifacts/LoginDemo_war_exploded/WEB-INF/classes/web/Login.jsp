<%--
  Created by IntelliJ IDEA.
  User: DemonHunter
  Date: 2020/2/20
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>LOGIN</title>
  </head>
  <body>
    <form action="/LoginDemo/LoginServlet" method="post">
      用户名:<input type="text" name="phone"><br>
      密码:<input type="password" name="password"><br>
      <input type="submit" value="登录">
    </form>
  </body>
</html>
