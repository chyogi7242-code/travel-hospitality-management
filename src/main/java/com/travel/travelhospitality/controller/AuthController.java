package com.travel.travelhospitality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travelhospitality.dto.AuthRequest;
import com.travel.travelhospitality.dto.AuthResponse;
import com.travel.travelhospitality.dto.RegisterRequest;
import com.travel.travelhospitality.entity.User;
import com.travel.travelhospitality.security.JwtService;
import com.travel.travelhospitality.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    // Register User
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        userService.register(user);

        return "User Registered Successfully";
    }

    // Login User
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        User user = userService.findByUsername(request.getUsername());

        if (user == null) {
            throw new RuntimeException("User Not Found");
        }

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtService.generateToken(
                user.getUsername(),
                user.getRole());

        return new AuthResponse(token);
    }
}