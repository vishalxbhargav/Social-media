package com.vishalxbhargav.service;

import com.vishalxbhargav.models.Post;

import java.util.List;

public interface PostService {
    Post createNewPost(Post post, Integer UserId) throws  Exception;
    String deletePost(Integer postId,Integer UserId) throws Exception;
    List<Post> findPostByUserId(Integer userId);
    Post findPostById(Integer postId) throws Exception;
    List<Post> findAllPost();
    Post savedPost(Integer postId,Integer userId) throws Exception;
    Post likePost(Integer postId,Integer userId) throws Exception;
}
