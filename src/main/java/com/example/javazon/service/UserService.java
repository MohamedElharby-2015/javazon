package com.example.javazon.service;

import com.example.javazon.entities.User;
import com.example.javazon.entities.dtos.UserDto;
import com.example.javazon.entities.dtos.UserLoginDto;
import com.example.javazon.entities.dtos.UserRegisterDto;
import com.example.javazon.exceptions.EntityAlreadyExistedException;
import com.example.javazon.exceptions.EntityNotFoundException;
import com.example.javazon.model.AuthResponse;
import com.example.javazon.repository.UserRepository;
import com.example.javazon.security.JwtUtil;
import com.example.javazon.service.mappers.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    JwtUtil jwtUtil;
    private NotificationProducerService notificationProducerService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserRegisterDto registerUser(UserRegisterDto userRegisterDto) {
        log.info("Adding User {}" ,userRegisterDto.getUserName());
        if (userRepository.existsByUserNameNative(userRegisterDto.getUserName())==1)
        {
            throw new EntityAlreadyExistedException("UserName Already Existed");
        }
        User user = userMapper.toEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        UserRegisterDto userSavedDto = userMapper.toUserRegistrationDto(userRepository.save(user));
        if(userSavedDto != null){
            notificationProducerService.sendWelcomeEmail(userSavedDto.getEmail());
        }

        return userSavedDto;
    }

    public AuthResponse login(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmail(userLoginDto.getEmail()).orElseThrow(
                ()-> new EntityNotFoundException("Invalid Email Or Password")
        );
        if (!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        String  token = jwtUtil.generateToken(user);


        AuthResponse authResponse = new AuthResponse();
            authResponse.setUser(userMapper.toUserDto(user));
            authResponse.setMessage("Login successful");
            authResponse.setToken(token);
            return authResponse;
        }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return userMapper.toDto(users);
    }

    public UserDto getUserById(int id)
    {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("user not found"));

        return userMapper.toUserDto(user);
    }

    public UserRegisterDto updateUser(int id, UserRegisterDto updatedUser)
    {
          User currentuser = userRepository.findById(id).orElseThrow(
                  ()-> new RuntimeException("user not found with id: "+ id));
        userMapper.updateEntityFromDto(currentuser,updatedUser);
        if (updatedUser.getPassword()!=null){
            currentuser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        userRepository.save(currentuser);

         return userMapper.toUserRegistrationDto(currentuser);
    }

    public void deleteUser(int id) {
        if (!userRepository.existsById(id)){
            throw new RuntimeException("user not found eith id: "+id);
        }
        userRepository.deleteById(id);
    }
}
