package com.example.javazon.model;

import com.example.javazon.entities.User;
import org.antlr.v4.runtime.Token;

public class AuthResponse {
    private User user;
    private String message;
    private String token;

    public AuthResponse(){}

    public AuthResponse(User user, String message, String token) {
        this.user = user;
        this.message = message;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
