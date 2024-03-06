package com.vishalxbhargav.service;

import com.vishalxbhargav.models.Comment;

public interface CommentService {
    public Comment createComment(Comment comment, Integer userId, Integer postId) throws Exception;
    public Comment findCommentsById(Integer commentId) throws Exception;
    public Comment likeComments(Integer commentId, Integer userId) throws Exception;
}
