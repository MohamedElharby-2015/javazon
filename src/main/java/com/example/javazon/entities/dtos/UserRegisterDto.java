package com.example.javazon.entities.dtos;

public class UserRegisterDto {
    private String userName;
    private String email;
    private String password;

    public UserRegisterDto() {}

    public UserRegisterDto(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {return password;}

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {this.password = password;}
}
