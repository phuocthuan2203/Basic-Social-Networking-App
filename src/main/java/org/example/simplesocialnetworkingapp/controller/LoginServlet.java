package org.example.simplesocialnetworkingapp.controller;

import org.example.simplesocialnetworkingapp.IUserAuth;
import org.example.simplesocialnetworkingapp.UserAuthImpl;
import org.example.simplesocialnetworkingapp.model.User;
import org.example.simplesocialnetworkingapp.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private IUserAuth userAuth;

    @Override
    public void init() throws ServletException {
        userAuth = new UserAuthImpl(new UserDAO());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userAuth.loginUser(username, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("loggedInUser", user);
            resp.sendRedirect("home.jsp"); // Redirect to a home page after login
        } else {
            req.setAttribute("error", "Invalid username or password.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
