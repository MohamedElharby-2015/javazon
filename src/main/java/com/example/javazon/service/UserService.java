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
import java.util.List;


@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationProducerService notificationProducerService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserRegisterDto registerUser(UserRegisterDto userRegisterDto) {
        log.info("Adding User {}" ,userRegisterDto.getUserName());

        if (userRepository.existsByUserNameNative(userRegisterDto.getUserName())==1)
        {
            throw new RuntimeException("User Already Existed");
        }
           User user = userMapper.toEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        UserRegisterDto userSavedDto = userMapper.toUserRegistrationDto(userRepository.save(user));
        if(userSavedDto != null){
            notificationProducerService.sendWelcomeEmail(userSavedDto.getEmail());
        }

        return userSavedDto;
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
