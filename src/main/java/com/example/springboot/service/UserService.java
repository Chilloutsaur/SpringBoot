package com.example.springboot.service;


import com.example.springboot.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

    void addUser(User user);

    void updateUser(Long id, User user);

}
