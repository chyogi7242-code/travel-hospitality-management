package com.travel.travelhospitality.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travel.travelhospitality.entity.User;
import com.travel.travelhospitality.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register User
    public User register(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    // Find User
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    // Save User
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Get User By Id
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete User
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}