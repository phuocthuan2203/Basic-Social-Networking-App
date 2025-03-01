package org.example.simplesocialnetworkingapp.posts_user.dao;

import org.example.simplesocialnetworkingapp.common.util.DBConnectionUtil;
import org.example.simplesocialnetworkingapp.domain.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

    public List<Post> findPostsByUser(int userId) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE user_id = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setUserId(rs.getInt("user_id"));
                post.setTitle(rs.getString("title"));
                post.setBody(rs.getString("body"));
                post.setStatus(rs.getString("status"));
                post.setCreatedAt(rs.getTimestamp("created_at"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public Post findPostById(int postId) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, postId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setUserId(rs.getInt("user_id"));
                post.setTitle(rs.getString("title"));
                post.setBody(rs.getString("body"));
                post.setStatus(rs.getString("status"));
                post.setCreatedAt(rs.getTimestamp("created_at"));
                return post;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertPost(Post post) {
        String sql = "INSERT INTO posts (user_id, title, body, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, post.getUserId());
            stmt.setString(2, post.getTitle());
            stmt.setString(3, post.getBody());
            stmt.setString(4, post.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePost(Post post) {
        String sql = "UPDATE posts SET title = ?, body = ?, status = ? WHERE id = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getBody());
            stmt.setString(3, post.getStatus());
            stmt.setInt(4, post.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePost(int postId) {
        String sql = "DELETE FROM posts WHERE id = ?";
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, postId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
