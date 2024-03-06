package com.vishalxbhargav.service;

import com.vishalxbhargav.models.Post;
import com.vishalxbhargav.models.User;
import com.vishalxbhargav.repository.PostRepository;
import com.vishalxbhargav.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService{
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Post createNewPost(Post post, Integer UserId) throws Exception {
        post.setUser(userService.findUserById(UserId));
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public String deletePost(Integer postId, Integer UserId) throws Exception {
        User user=userService.findUserById(UserId);
        Post post =findPostById(postId);
        if(!Objects.equals(post.getId(), user.getId())){
            throw new Exception("you can't delete another users post");
        }
        postRepository.delete(post);
        return "Successfully Post deleted";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> byId = postRepository.findById(postId);
        if(byId.isEmpty()) throw new Exception("can't find post");
        return byId.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        User user=userService.findUserById(userId);
        Post post =findPostById(postId);
        if(user.getSavedPost().contains(post)) user.getSavedPost().remove(post);
        else user.getSavedPost().add(post);
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        User user=userService.findUserById(userId);
        Post post =findPostById(postId);
        if(post.getLiked().contains(user)) post.getLiked().remove(user);
        else post.getLiked().add(user);
        return postRepository.save(post);
    }
}
