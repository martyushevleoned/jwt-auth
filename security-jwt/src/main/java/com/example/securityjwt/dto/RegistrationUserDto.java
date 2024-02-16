package com.example.securityjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationUserDto {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}