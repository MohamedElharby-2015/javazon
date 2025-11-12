package com.example.javazon.controller;

import com.example.javazon.entities.User;
import com.example.javazon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
