<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://jakarta.apache.org/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>My Posts</title>
</head>
<body>
<h2>My Posts</h2>
<a href="manageOwnPosts?action=new">New Post</a>
<table border="1">
    <tr>
        <th>Title</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="post" items="${posts}">
        <tr>
            <td>${post.title}</td>
            <td>${post.status}</td>
            <td>
                <a href="manageOwnPosts?action=edit&id=${post.id}">Edit</a>
                <a href="manageOwnPosts?action=delete&id=${post.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>