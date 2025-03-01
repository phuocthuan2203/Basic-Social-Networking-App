<%--
  Created by IntelliJ IDEA.
  User: thuannp4
  Date: 26/2/25
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
</head>
<body>
<h2>Login</h2>
<c:if test="${not empty error}">
  <p style="color:red;">${error}</p>
</c:if>
<c:if test="${not empty message}">
  <p style="color:green;">${message}</p>
</c:if>
<form action="login" method="post">
  <label for="username">Username:</label>
  <input type="text" name="username" id="username" required/><br/>
  <label for="password">Password:</label>
  <input type="password" name="password" id="password" required/><br/>
  <input type="submit" value="Login"/>
</form>
<p>Don't have an account? <a href="register">Register here</a>.</p>
</body>
</html>
