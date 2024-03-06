package com.vishalxbhargav.service;

import com.vishalxbhargav.config.JwtProvider;
import com.vishalxbhargav.models.User;
import com.vishalxbhargav.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImplementaion implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User registeruser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Integer Id) throws Exception {
        Optional<User> user= userRepository.findById(Id);
        if(user.isPresent()) return user.get();
        throw new Exception("user not found with "+Id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {
        User user1=findUserById(userId1);
        User user2=findUserById(userId2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowing().add(user2.getId());
        userRepository.save(user1);
        userRepository.save(user2);
        return user1;
    }

    @Override
    public User updateUser(User user,Integer id) throws Exception {
        User oldUser=findUserById(id);
        if(user.getFirstName().isEmpty()) user.setFirstName(oldUser.getFirstName());
        if(user.getLastName().isEmpty()) user.setLastName(oldUser.getLastName());
        if(user.getEmail().isEmpty()) user.setEmail(oldUser.getEmail());
        registeruser(user);
        return user;
    }
    @Override
    public List<User> searchUserList(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User getUserByJwtToken(String token) {
        String email= JwtProvider.getEmailFromJwtToken(token);
        return userRepository.findUserByEmail(email);
    }
}
