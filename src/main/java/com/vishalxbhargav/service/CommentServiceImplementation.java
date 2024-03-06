package com.vishalxbhargav.service;

import com.vishalxbhargav.models.Comment;
import com.vishalxbhargav.models.Post;
import com.vishalxbhargav.models.User;
import com.vishalxbhargav.repository.CommentRepository;
import com.vishalxbhargav.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Override
    public Comment createComment(Comment comment, Integer userId, Integer postId) throws Exception {
        User user =userService.findUserById(userId);
        Post post = postService.findPostById(postId);
        comment.setUser(user);
        comment.setCreateAt(LocalDateTime.now());
        Comment com=commentRepository.save(comment);
        post.getComments().add(com);
        postRepository.save(post);
        return com;
    }

    @Override
    public Comment findCommentsById(Integer commentId) throws Exception {
        Optional<Comment> cmt = commentRepository.findById(commentId);
        if(cmt.isEmpty()) throw new Exception("Can't find Any comment for this "+commentId);
        return cmt.get();
    }

    @Override
    public Comment likeComments(Integer commentId, Integer userId) throws Exception {
        User user=userService.findUserById(userId);
        Comment comment=findCommentsById(commentId);
        if(comment.getLikes().contains(user)) comment.getLikes().remove(user);
        else comment.getLikes().add(user);
        return commentRepository.save(comment);
    }
}
