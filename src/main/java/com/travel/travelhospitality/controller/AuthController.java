package com.travel.travelhospitality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travelhospitality.dto.AuthRequest;
import com.travel.travelhospitality.dto.AuthResponse;
import com.travel.travelhospitality.dto.RefreshTokenRequest;
import com.travel.travelhospitality.dto.RegisterRequest;
import com.travel.travelhospitality.entity.RefreshToken;
import com.travel.travelhospitality.entity.User;
import com.travel.travelhospitality.security.JwtService;
import com.travel.travelhospitality.service.RefreshTokenService;
import com.travel.travelhospitality.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        String accessToken =
                jwtService.generateToken(user.getUsername(), user.getRole());

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user);

        return new AuthResponse(
                accessToken,
                refreshToken.getToken());
    }

    // Refresh Access Token
    @PostMapping("/refresh")
    public AuthResponse refreshToken(
            @RequestBody RefreshTokenRequest request) {

        RefreshToken refreshToken =
                refreshTokenService.findByToken(request.getRefreshToken());

        if (refreshToken == null) {
            throw new RuntimeException("Invalid Refresh Token");
        }

        if (refreshTokenService.isExpired(refreshToken)) {
            throw new RuntimeException("Refresh Token Expired");
        }

        User user = refreshToken.getUser();

        String accessToken =
                jwtService.generateToken(user.getUsername(), user.getRole());

        return new AuthResponse(
                accessToken,
                refreshToken.getToken());
    }
}