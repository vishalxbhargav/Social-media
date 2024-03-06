package com.vishalxbhargav.controller;

import com.vishalxbhargav.models.Post;
import com.vishalxbhargav.models.User;
import com.vishalxbhargav.response.ApiResponse;
import com.vishalxbhargav.service.PostService;
import com.vishalxbhargav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @PostMapping("api/posts/user")
    public ResponseEntity<?> createPost(@RequestHeader("Authorization") String token,@RequestBody Post post){
        try {

            User user=userService.getUserByJwtToken(token);
            Post createdPost=postService.createNewPost(post,user.getId());
            return new ResponseEntity<>(createdPost, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("api/user/{postId}")
    public ResponseEntity<?> deletePost(@RequestHeader("Authorization") String token,@PathVariable Integer postId){
        try {
            User user=userService.getUserByJwtToken(token);
            String message=postService.deletePost(postId,user.getId());
            ApiResponse api=new ApiResponse(message,true);
            return new ResponseEntity<>(api,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("api/posts/{postId}")
    public ResponseEntity<?> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        try {
            Post post =postService.findPostById(postId);
            return new ResponseEntity<>(post,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("api/posts/user")
    public ResponseEntity<?> findUsersPost(@RequestHeader("Authorization") String token,@PathVariable Integer userId){
        try {
            User user=userService.getUserByJwtToken(token);
            List<Post>  posts=postService.findPostByUserId(userId);
            return new ResponseEntity<>(posts,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("posts")
    public ResponseEntity<?> getAllPost(){
        try {

            List<Post> posts=postService.findAllPost();
            return new ResponseEntity<>(posts,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("api/posts/save/{postId}")
    public ResponseEntity<?> savePostHandler(@RequestHeader("Authorization") String token,@PathVariable Integer postId) throws Exception {
        try {
            User user=userService.getUserByJwtToken(token);
            Post post=postService.savedPost(postId,user.getId());
            return new ResponseEntity<>(post,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("api/posts/like/{postId}")
    public ResponseEntity<?> likePostHandler(@RequestHeader("Authorization") String token,@PathVariable Integer postId) throws Exception {
        try {
            User user=userService.getUserByJwtToken(token);
            Post post=postService.likePost(postId,user.getId());
            return new ResponseEntity<>(post,HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
