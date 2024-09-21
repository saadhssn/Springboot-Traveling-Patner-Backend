package com.internode_studios.spring_boot_application.user.service;

import com.internode_studios.spring_boot_application.Jwt.service.JwtUtil;
import com.internode_studios.spring_boot_application.user.model.User;
import com.internode_studios.spring_boot_application.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpService otpService;

    // Register a new user
    public User registerUser(User user) {
        // Set ID to null to ensure a new record is inserted
        user.setId(null);
        user.setIsOtpVerified(false);  // Initially set OTP verification to false
        userRepository.save(user);

        // Generate OTP for the user
        otpService.generateOtp(user.getMobileNumber());
        return user;
    }

    // Verify OTP and generate token
    public String verifyOtp(String mobileNumber, String otp, JwtUtil jwtUtil) {
        if (otpService.validateOtp(mobileNumber, otp)) {
            List<User> users = userRepository.findByMobileNumber(mobileNumber);

            if (!users.isEmpty()) {
                User user = users.get(0);  // Take the first user in the list
                user.setIsOtpVerified(true);
                userRepository.save(user);  // Update OTP verification status

                return jwtUtil.generateToken(user.getMobileNumber());
            }
        }
        return null;  // If no valid OTP or user is found
    }
}
