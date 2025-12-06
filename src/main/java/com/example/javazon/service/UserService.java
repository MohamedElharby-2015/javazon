package com.example.javazon.service;

import com.example.javazon.entities.User;
import com.example.javazon.entities.dtos.UserDto;
import com.example.javazon.entities.dtos.UserLoginDto;
import com.example.javazon.entities.dtos.UserRegisterDto;
import com.example.javazon.repository.UserRepository;
import com.example.javazon.service.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(UserRegisterDto userRegisterDto) {
        User user = userMapper.toEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        userRepository.save(user);
        return "User Saved";
    }


    public boolean login(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmail(userLoginDto.getEmail());
        if (user != null) {
            return passwordEncoder.matches(userLoginDto.getPassword() ,user.getPassword());
        }
        return false;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return userMapper.toDto(users);
    }

    public UserDto getUserById(int id)
    {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("user not found"));

        return userMapper.toDto(user);
    }

    public UserDto updateUser(int id, User updatedUser) {

        return userRepository.findById(id).map(user -> {
            user.setUserName(updatedUser.getUserName());
               return userRepository.save(user);
        }).orElse(null);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
