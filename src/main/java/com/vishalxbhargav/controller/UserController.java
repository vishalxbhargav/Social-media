package com.vishalxbhargav.controller;

import java.util.List;
import java.util.Optional;

import com.vishalxbhargav.config.jwtConstent;
import com.vishalxbhargav.repository.UserRepository;
import com.vishalxbhargav.service.UserService;
import com.vishalxbhargav.service.UserServiceImplementaion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vishalxbhargav.models.User;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	UserService userService;

	@GetMapping("user/id/{id}")
	public User getUser(@PathVariable Integer id) throws Exception {
		return userService.findUserById(id);
	}
	@GetMapping("user")
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	@PutMapping("api/users")
	public User updateUser(@RequestHeader("Authorization") String token,@RequestBody User user) throws Exception {
		User newUser=userService.getUserByJwtToken(token);
		return userService.updateUser(user, newUser.getId());
	}
	@GetMapping("user/email/{email}")
	public User getByEmail(@PathVariable String email){
		return userService.findUserByEmail(email);
	}
	@DeleteMapping
	public void deleteUserById(@PathVariable Integer id){
        userRepository.deleteById(id);
    }

	@PutMapping("api/user/follow/{user2}")
	public User followingHandler(@RequestHeader("Authorization") String token,@PathVariable Integer user2) throws Exception {
		User reqUser=userService.getUserByJwtToken(token);
		return userService.followUser(reqUser.getId(), user2);
	}
	@GetMapping("search")
	public  List<User> searchUsers(@RequestParam String query){
		return userService.searchUserList(query);
	}
	@GetMapping("api/users/profile")
	public ResponseEntity<?> getUserByToken(@RequestHeader("Authorization") String token){
		try {
			User user= userService.getUserByJwtToken(token);
			return new ResponseEntity<>(user, HttpStatus.OK);

		} catch (Exception e) {

			throw new RuntimeException(e);
		}
	}
}

