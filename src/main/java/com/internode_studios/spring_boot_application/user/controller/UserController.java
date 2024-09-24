package com.internode_studios.spring_boot_application.user.controller;

import com.internode_studios.spring_boot_application.license.model.License;
import com.internode_studios.spring_boot_application.user.model.User;
import com.internode_studios.spring_boot_application.Jwt.service.JwtUtil;
import com.internode_studios.spring_boot_application.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    // Sign-up method to register user and send OTP
    @PostMapping("/login")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        if (savedUser != null) {
            return ResponseEntity.ok(savedUser);
        }
        return ResponseEntity.status(404).body("User not found.");
    }

    // Verify OTP method
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody User userRequest) {
        String token = userService.verifyOtp(userRequest.getMobileNumber(), userRequest.getOtp(), jwtUtil);
        if (token != null) {
            return ResponseEntity.ok("Bearer " + token);
        }
        return ResponseEntity.status(401).body("Invalid OTP.");
    }
}
