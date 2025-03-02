<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Post</title>
</head>
<body>
<h2>Edit Post</h2>
<form action="manageOwnPosts?action=update&id=${post.id}" method="post">
    <label for="title">Title:</label>
    <input type="text" name="title" id="title" value="${post.title}" required/><br/>
    <label for="body">Body:</label>
    <textarea name="body" id="body" required>${post.body}</textarea><br/>
    <label for="status">Status:</label>
    <select name="status" id="status">
        <option value="draft" ${post.status == 'draft' ? 'selected' : ''}>Draft</option>
        <option value="published" ${post.status == 'published' ? 'selected' : ''}>Published</option>
    </select><br/>
    <input type="submit" value="Update"/>
</form>
</body>
</html>