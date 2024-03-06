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
    public User followUser(Integer reqUserId, Integer userId2) throws Exception {
        User reqUser=findUserById(reqUserId);
        User user2=findUserById(userId2);

        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowing().add(user2.getId());
        userRepository.save(reqUser);
        userRepository.save(user2);
        return reqUser;
    }

    @Override
    public User updateUser(User user,Integer id) throws Exception {
        User oldUser=findUserById(id);
        if(!user.getFirstName().isEmpty()) oldUser.setFirstName(user.getFirstName());
        if(!user.getLastName().isEmpty()) oldUser.setLastName(user.getLastName());
        if(!user.getEmail().isEmpty()) oldUser.setEmail(user.getEmail());
        if(!user.getGender().isEmpty()) oldUser.setGender(user.getGender());
        registeruser(oldUser);
        return oldUser;
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
