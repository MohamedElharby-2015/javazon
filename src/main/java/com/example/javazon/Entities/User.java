package com.example.javazon.Entities;

import jakarta.persistence.*;

@Entity
@Table (name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String userName;
    private String firstName;
    private String secondnName;
    private String email;
    private String password;


    public User(int userId, String userName, String firstName, String secondnName, String email, String password) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.secondnName = secondnName;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondnName() {
        return secondnName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondnName(String secondnName) {
        this.secondnName = secondnName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
