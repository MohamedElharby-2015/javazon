package com.example.javazon.service;

import com.example.javazon.entities.User;
import com.example.javazon.entities.dtos.UserDto;
import com.example.javazon.entities.dtos.UserLoginDto;
import com.example.javazon.entities.dtos.UserRegisterDto;
import com.example.javazon.repository.UserRepository;
import com.example.javazon.service.mappers.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.javazon.service.ProductService.log;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(UserRegisterDto userRegisterDto) {
        log.info("Adding User {}" ,userRegisterDto.getUserName());

        if (userRepository.existsByUserName(userRegisterDto.getUserName()))
        {
            throw new RuntimeException("User Already Existed");
        }

       return userRepository.save(userMapper.toEntity(userRegisterDto));

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

    public UserRegisterDto updateUser(int id, UserRegisterDto updatedUser)
    {
          User currentuser = userRepository.findById(id).orElseThrow(
                  ()-> new RuntimeException("user not found with id: "+ id));
        userMapper.updateEntityFromDto(currentuser,updatedUser);

        userRepository.save(currentuser);

         return userMapper.toUserRegistrationDto(updatedUser);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
