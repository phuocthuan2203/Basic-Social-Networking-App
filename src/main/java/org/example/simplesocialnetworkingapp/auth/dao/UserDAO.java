package org.example.simplesocialnetworkingapp.auth.dao;

import org.example.simplesocialnetworkingapp.common.util.DBConnectionUtil;
import org.example.simplesocialnetworkingapp.domain.model.User;

import java.sql.*;

public class UserDAO {

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                u.setCreatedAt(rs.getTimestamp("created_at"));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // or throw an exception
    }

    public void insertUser(User user) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
