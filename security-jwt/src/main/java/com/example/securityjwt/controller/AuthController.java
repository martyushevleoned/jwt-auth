package com.example.securityjwt.controller;

import com.example.securityjwt.dto.JwtRequest;
import com.example.securityjwt.dto.RegistrationUserDto;
import com.example.securityjwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> authUser(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createRegistrationToken(registrationUserDto);
    }

}
