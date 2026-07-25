package com.journal_app.java.dto;


import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;
}
