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
  <title>Home</title>
</head>
<body>
<h2>Welcome, ${sessionScope.loggedInUser.username}</h2>
<p>Your role: ${sessionScope.loggedInUser.role}</p>
<p><a href="logout">Logout</a></p>
</body>
</html>
