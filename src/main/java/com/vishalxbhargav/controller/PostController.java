package com.vishalxbhargav.controller;

import com.vishalxbhargav.models.Post;
import com.vishalxbhargav.response.ApiResponse;
import com.vishalxbhargav.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("user/{userId}")
    public ResponseEntity<?> createPost(@RequestBody Post post, @PathVariable Integer userId){
        try {
            Post createdPost=postService.createNewPost(post,userId);
            return new ResponseEntity<>(createdPost, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{postId}/user/{userId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer postId,@PathVariable Integer userId){
        try {
            String message=postService.deletePost(postId,userId);
            ApiResponse api=new ApiResponse(message,true);
            return new ResponseEntity<>(api,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("{postId}")
    public ResponseEntity<?> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        try {
            Post post =postService.findPostById(postId);
            return new ResponseEntity<>(post,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("user/{userId}")
    public ResponseEntity<?> findUsersPost(@PathVariable Integer userId){
        try {
            List<Post>  posts=postService.findPostByUserId(userId);
            return new ResponseEntity<>(posts,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping()
    public ResponseEntity<?> getAllPost(){
        try {
            List<Post> posts=postService.findAllPost();
            return new ResponseEntity<>(posts,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("save/{postId}/user/{userId}")
    public ResponseEntity<?> savePostHandler(@PathVariable Integer postId,@PathVariable Integer userId) throws Exception {
        try {
            Post post=postService.savedPost(postId,userId);
            return new ResponseEntity<>(post,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("like/{postId}/user/{userId}")
    public ResponseEntity<?> likePostHandler(@PathVariable Integer postId,@PathVariable Integer userId) throws Exception {
        try {
            Post post=postService.likePost(postId,userId);
            return new ResponseEntity<>(post,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
