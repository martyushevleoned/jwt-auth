package com.example.securityjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtRequest {
    private String username;
    private String password;
}
