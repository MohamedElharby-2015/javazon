package com.example.javazon.controller;

import com.example.javazon.entities.User;
import com.example.javazon.entities.dtos.UserDto;
import com.example.javazon.entities.dtos.UserLoginDto;
import com.example.javazon.entities.dtos.UserRegisterDto;
import com.example.javazon.model.AuthResponse;
import com.example.javazon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// needed for user cruds only
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

//    check () by name >>Done
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

//    Make it like Category(make mapper method in update *updateEntityFromDto*)>>Done
    @PutMapping("/{id}")
    public UserRegisterDto updateUser(@PathVariable int id, @RequestBody UserRegisterDto updatedUser)
    {
        return userService.updateUser(id,updatedUser);
    }


//     check by if () :
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {

        userService.deleteUser(id);
        return "User deleted successfully!";
    }
}
