package com.example.javazon.service;

import com.example.javazon.entities.User;
import com.example.javazon.entities.dtos.UserDto;
import com.example.javazon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(User user) {
        String rawPassword = user.getPassword();
        String hashedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return "User Saved";
    }


    public boolean login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }



    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();


        for (User user : users) {
            UserDto dto = new UserDto();
            dto.setUserId(user.getUserId());
            dto.setUserName(user.getUserName());
            dto.setEmail(user.getEmail());
            userDtos.add(dto);
        }

        return userDtos;

    }

    public User getUserById(int id)
    {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(int id, User updatedUser) {

        return userRepository.findById(id).map(user -> {
            user.setUserName(updatedUser.getUserName());
               return userRepository.save(user);
        }).orElse(null);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
