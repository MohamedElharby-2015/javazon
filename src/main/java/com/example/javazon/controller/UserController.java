package com.example.javazon.controller;

import com.example.javazon.entities.dtos.UserDto;
import com.example.javazon.entities.dtos.UserLoginDto;
import com.example.javazon.entities.dtos.UserRegisterDto;
import com.example.javazon.model.AuthResponse;
import com.example.javazon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/javazon/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public UserRegisterDto register(@RequestBody UserRegisterDto userRegisterDto)
    {
        return userService.registerUser(userRegisterDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(userService.login(userLoginDto));
    }

    @GetMapping("all")
    public List<UserDto> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable int id)
    {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserRegisterDto updateUser(@PathVariable int id, @RequestBody UserRegisterDto updatedUser)
    {
        return userService.updateUser(id,updatedUser);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {

        userService.deleteUser(id);
        return "User deleted successfully!";
    }
}
