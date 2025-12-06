package com.example.javazon.controller;

import com.example.javazon.entities.User;
import com.example.javazon.entities.dtos.UserDto;
import com.example.javazon.entities.dtos.UserLoginDto;
import com.example.javazon.entities.dtos.UserRegisterDto;
import com.example.javazon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

//    check () by name
    @PostMapping("/register")
    public String register(@RequestBody UserRegisterDto userRegisterDto)
    {
        return userService.registerUser(userRegisterDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto userLoginDto) {
        boolean success = userService.login(userLoginDto);
        return success ? "Login successful!" : "Invalid email or password!";
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

//    Make it like Category(make mapper method in update *updateEntityFromDto*)
    @PutMapping("/{id}")
    public UserRegisterDto updateUser(@PathVariable int id, @RequestBody User user)
    {
        return userService.updateUser(id, user);
    }


//     check by if () :
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {

        userService.deleteUser(id);
        return "User deleted successfully!";
    }
}
