package com.example.securityjwt.controller;

import com.example.securityjwt.config.SecurityConfig;
import com.example.securityjwt.dto.AppError;
import com.example.securityjwt.dto.JwtRequest;
import com.example.securityjwt.dto.JwtResponse;
import com.example.securityjwt.dto.RegistrationUserDto;
import com.example.securityjwt.model.entity.Role;
import com.example.securityjwt.model.entity.User;
import com.example.securityjwt.service.UserService;
import com.example.securityjwt.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityConfig securityConfig;

    @GetMapping("/auth")
    public ResponseEntity<?> adminData(@RequestBody JwtRequest authRequest) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.MULTI_STATUS);
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Пользователь с указанным именем уже существует"), HttpStatus.UNAUTHORIZED);
        }

        userService.createNewUser(
                new User(
                        registrationUserDto.getEmail(),
                        registrationUserDto.getUsername(),
                        securityConfig.passwordEncoder().encode(registrationUserDto.getPassword())
                )
        );

        String token = jwtTokenUtils.generateToken(userService.loadUserByUsername(registrationUserDto.getUsername()));
        return ResponseEntity.ok(new JwtResponse(token));
    }

}
