package org.example.simplesocialnetworkingapp.auth.controller;

import org.example.simplesocialnetworkingapp.auth.service.IUserAuth;
import org.example.simplesocialnetworkingapp.auth.service.UserAuthImpl;
import org.example.simplesocialnetworkingapp.auth.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private IUserAuth userAuth;

    @Override
    public void init() throws ServletException {
        userAuth = new UserAuthImpl(new UserDAO());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean success = userAuth.registerUser(username, password);
        if (success) {
            req.setAttribute("message", "Registration successful. Please log in.");
            req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Username already taken. Please choose another.");
            req.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(req, resp);
        }
    }
}