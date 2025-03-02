package org.example.simplesocialnetworkingapp.posts_user.service;

import java.util.List;

import org.example.simplesocialnetworkingapp.domain.model.Post;

public interface IOwnPostManagement {
    List<Post> getPostsForUser(int userId);
    Post getPostById(int postId, int userId);
    void createPost(int userId, String title, String body, String status);
    void updatePost(int postId, String title, String body, String status, int userId);
    void deletePost(int postId, int userId);
}
