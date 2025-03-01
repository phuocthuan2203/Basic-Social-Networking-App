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
    <title>Register</title>
</head>
<body>
<h2>Register</h2>
<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>
<form action="register" method="post">
    <label for="username">Username:</label>
    <input type="text" name="username" id="username" required/><br/>
    <label for="password">Password:</label>
    <input type="password" name="password" id="password" required/><br/>
    <input type="submit" value="Register"/>
</form>
<p>Already have an account? <a href="login">Login here</a>.</p>
</body>
</html>
