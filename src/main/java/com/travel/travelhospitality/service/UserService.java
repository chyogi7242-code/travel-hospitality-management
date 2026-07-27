package com.travel.travelhospitality.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.travelhospitality.entity.User;
import com.travel.travelhospitality.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register User
    public User register(User user) {
        return userRepository.save(user);
    }

    // Find User by Username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    // Get User by ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Get All Users
    public java.util.List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete User
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}