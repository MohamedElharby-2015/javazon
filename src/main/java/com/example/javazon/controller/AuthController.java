package com.example.javazon.controller;

//import com.example.javazon.security.JwtUtil;
import com.example.javazon.entities.User;
import com.example.javazon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


// needed for user auth only
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private JwtUtil jwtUtil;

//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!user.getPassword().equals(password)) {
//            throw new RuntimeException("Invalid credentials");
//        }
//
//        return jwtUtil.generateToken(username);
//    }
}
