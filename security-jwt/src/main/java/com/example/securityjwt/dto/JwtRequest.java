package com.example.securityjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtRequest {
    private String username;
    private String password;
}
