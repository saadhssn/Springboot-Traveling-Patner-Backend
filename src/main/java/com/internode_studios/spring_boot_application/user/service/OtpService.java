package com.internode_studios.spring_boot_application.user.service;

import com.internode_studios.spring_boot_application.user.model.User;
import com.internode_studios.spring_boot_application.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtpService {

    @Autowired
    private UserRepository userRepository;

    // Method to generate OTP for users with the same mobile number
    public void generateOtp(String mobileNumber) {
        List<User> users = userRepository.findByMobileNumber(mobileNumber);

        if (!users.isEmpty()) {
            for (User user : users) {
                String otp = generateRandomOtp();
                user.setOtp(otp);
                userRepository.save(user);  // Save OTP for each user
            }
        }
    }

    // Method to only update OTP for a specific user
    public void updateOtp(User user) {
        String otp = generateRandomOtp();
        user.setOtp(otp);
        userRepository.save(user);  // Update OTP without modifying other fields
    }

    // Method to validate OTP
    public boolean validateOtp(String mobileNumber, String otp) {
        User user = userRepository.findByMobileNumberAndOtp(mobileNumber, otp);
        return user != null;
    }

    // Generate a random OTP (for simplicity, using 4 digits here)
    private String generateRandomOtp() {
        return String.valueOf((int) (Math.random() * 9000) + 1000);
    }
}
