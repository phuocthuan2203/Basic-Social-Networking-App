package org.example.simplesocialnetworkingapp.posts_user.service;

import org.example.simplesocialnetworkingapp.posts_user.dao.PostDAO;
import org.example.simplesocialnetworkingapp.domain.model.Post;

import java.util.List;

public class OwnPostManagementImpl implements IOwnPostManagement {

    private final PostDAO postDAO;

    public OwnPostManagementImpl() {
        this.postDAO = new PostDAO();
    }

    @Override
    public List<Post> getPostsForUser(int userId) {
        return postDAO.findPostsByUser(userId);
    }

    @Override
    public Post getPostById(int postId, int userId) {
        Post post = postDAO.findPostById(postId);
        if (post != null && post.getUserId() == userId) {
            return post;
        }
        return null;
    }

    @Override
    public void createPost(int userId, String title, String body, String status) {
        Post post = new Post();
        post.setUserId(userId);
        post.setTitle(title);
        post.setBody(body);
        post.setStatus(status);
        postDAO.insertPost(post);
    }

    @Override
    public void updatePost(int postId, String title, String body, String status, int userId) {
        Post post = postDAO.findPostById(postId);
        if (post != null && post.getUserId() == userId) {
            post.setTitle(title);
            post.setBody(body);
            post.setStatus(status);
            postDAO.updatePost(post);
        }
    }

    @Override
    public void deletePost(int postId, int userId) {
        Post post = postDAO.findPostById(postId);
        if (post != null && post.getUserId() == userId) {
            postDAO.deletePost(postId);
        }
    }
}
