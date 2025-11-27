package com.example.javazon.controller;

import com.example.javazon.entities.User;
import com.example.javazon.entities.dtos.UserDto;
import com.example.javazon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user)
    {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        boolean success = userService.login(user.getEmail(),user.getPassword());
        return success ? "Login successful!" : "Invalid email or password!";
    }

    @GetMapping("/getAllUsers")
    public List<UserDto> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable int id)
    {
        return userService.getUserById(id);
    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user)
    {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "User deleted successfully!";
    }
}
