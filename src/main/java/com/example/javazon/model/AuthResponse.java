package com.example.javazon.model;


import com.example.javazon.entities.dtos.UserDto;

public class AuthResponse {
    private UserDto user;
    private String message;
    private String token;

    public AuthResponse(){}

    public AuthResponse(UserDto user, String message, String token) {
        this.user = user;
        this.message = message;
        this.token = token;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
