package com.vishalxbhargav.service;

import com.vishalxbhargav.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User registeruser(User user);
    public User findUserById(Integer Id) throws Exception;
    public User findUserByEmail(String email);
    public User followUser(Integer userId1,Integer userId2) throws Exception;
    public User updateUser(User user,Integer id) throws Exception;
    public List<User> searchUserList(String query);

    public User getUserByJwtToken(String jwt);
}
