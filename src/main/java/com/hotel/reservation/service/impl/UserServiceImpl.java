package com.hotel.reservation.service.impl;

import com.hotel.reservation.dto.*;
import com.hotel.reservation.entity.Role;
import com.hotel.reservation.entity.User;
import com.hotel.reservation.repository.UserRepository;
import com.hotel.reservation.service.UserService;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository){

        this.userRepository = userRepository;

    }



    @Override
    public AuthResponse register(RegisterRequest request){


        if(userRepository.findByEmail(request.getEmail()).isPresent()){

            return new AuthResponse(
                    "Email already exists",
                    null
            );

        }


        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.CUSTOMER)
                .build();


        userRepository.save(user);


        return new AuthResponse(
                "Registration successful",
                "dummy-token"
        );

    }



    @Override
    public AuthResponse login(LoginRequest request){


        User user = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);



        if(user == null ||
                !user.getPassword().equals(request.getPassword())){


            return new AuthResponse(
                    "Invalid credentials",
                    null
            );

        }



        return new AuthResponse(
                "Login successful",
                "dummy-token"
        );

    }

}