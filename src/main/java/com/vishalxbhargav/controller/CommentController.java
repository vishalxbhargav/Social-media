package com.vishalxbhargav.controller;

import com.vishalxbhargav.models.Comment;
import com.vishalxbhargav.models.User;
import com.vishalxbhargav.service.CommentService;
import com.vishalxbhargav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @PostMapping("api/comment/like/{commentId}")
    public Comment createdComment(@RequestHeader("Authorization") String token,@PathVariable  Integer commentId) throws Exception {
        User reqUser=userService.getUserByJwtToken(token);
        return commentService.likeComments(commentId,reqUser.getId());
    }
    @GetMapping("api/comment/id/{commentId}")
    public Comment getCommentById(@PathVariable Integer commentId) throws Exception {
        return commentService.findCommentsById(commentId);
    }
    @PostMapping("api/comment/post/{postId}")
    public Comment likeComment(@RequestHeader("Authorization") String token,Comment comment, @PathVariable  Integer postId) throws Exception {
        User reqUser=userService.getUserByJwtToken(token);
        return commentService.createComment(comment, reqUser.getId(),postId);
    }

}
