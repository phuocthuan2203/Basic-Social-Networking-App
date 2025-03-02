package org.example.simplesocialnetworkingapp.posts_user.controller;

import org.example.simplesocialnetworkingapp.domain.model.Post;
import org.example.simplesocialnetworkingapp.domain.model.User;
import org.example.simplesocialnetworkingapp.posts_user.service.IOwnPostManagement;
import org.example.simplesocialnetworkingapp.posts_user.service.OwnPostManagementImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/manageOwnPosts")
public class ManageOwnPostsServlet extends HttpServlet {

    private IOwnPostManagement ownPostManagement;

    @Override
    public void init() throws ServletException {
        ownPostManagement = new OwnPostManagementImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = ((User) session.getAttribute("loggedInUser")).getId();
        String action = req.getParameter("action");

        if ("new".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/views/posts_user/newPost.jsp").forward(req, resp);
        } else if ("edit".equals(action)) {
            int postId = Integer.parseInt(req.getParameter("id"));
            Post post = ownPostManagement.getPostById(postId, userId);
            req.setAttribute("post", post);
            req.getRequestDispatcher("/WEB-INF/views/posts_user/editPost.jsp").forward(req, resp);
        } else if ("delete".equals(action)) {
            int postId = Integer.parseInt(req.getParameter("id"));
            ownPostManagement.deletePost(postId, userId);
            resp.sendRedirect(req.getContextPath() + "/manageOwnPosts");
        } else {
            List<Post> posts = ownPostManagement.getPostsForUser(userId);
            req.setAttribute("posts", posts);
            req.getRequestDispatcher("/WEB-INF/views/posts_user/myPosts.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = ((User) session.getAttribute("loggedInUser")).getId();
        String action = req.getParameter("action");

        if ("create".equals(action)) {
            String title = req.getParameter("title");
            String body = req.getParameter("body");
            String status = req.getParameter("status");
            ownPostManagement.createPost(userId, title, body, status);
        } else if ("update".equals(action)) {
            int postId = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String body = req.getParameter("body");
            String status = req.getParameter("status");
            ownPostManagement.updatePost(postId, title, body, status, userId);
        }
        resp.sendRedirect(req.getContextPath() + "/manageOwnPosts");
    }
}