package com.example.securityjwt.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class AppError {

    public AppError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }

    private int status;
    public String message;
    private Date timestamp;
}