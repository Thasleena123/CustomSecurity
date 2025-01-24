package com.example.library.sevice;

import com.example.library.entity.UserEntity;
import com.example.library.repository.UserRepository;
import com.example.library.utility.CustomToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServices {
    @Autowired
    private UserRepository userRepository;
    public String signUp(UserEntity userEntity) {
        if (userRepository.findByUsername(userEntity.getUsername()) != null) {
            throw new RuntimeException("User already exists");
        }
        userRepository.save(userEntity);
        return "User successfully registered";
    }

    public String login(String username, String password) {
        UserEntity user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return CustomToken.generateToken(user.getUsername(), user.getDepartment());
        }
        throw new RuntimeException("Invalid username or password");
    }
}



