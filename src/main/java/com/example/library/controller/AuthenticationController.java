package com.example.library.controller;


import com.example.library.dto.LoginRequest;
import com.example.library.entity.UserEntity;
import com.example.library.sevice.AuthenticationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    private  final  AuthenticationServices authenticationServices;
    public AuthenticationController(AuthenticationServices authenticationServices) {
        this.authenticationServices = authenticationServices;
    }


    @PostMapping("/signup")
    public String signUp(@RequestBody UserEntity userEntity) {
        return authenticationServices.signUp(userEntity);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String token = authenticationServices.login(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(token);

    }
}
