<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Post</title>
</head>
<body>
<h2>New Post</h2>
<form action="manageOwnPosts?action=create" method="post">
    <label for="title">Title:</label>
    <input type="text" name="title" id="title" required/><br/>
    <label for="body">Body:</label>
    <textarea name="body" id="body" required></textarea><br/>
    <label for="status">Status:</label>
    <select name="status" id="status">
        <option value="draft">Draft</option>
        <option value="published">Published</option>
    </select><br/>
    <input type="submit" value="Create"/>
</form>
</body>
</html>