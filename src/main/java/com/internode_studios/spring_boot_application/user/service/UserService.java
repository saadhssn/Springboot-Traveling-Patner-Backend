package com.internode_studios.spring_boot_application.user.service;

import com.internode_studios.spring_boot_application.Jwt.service.JwtUtil;
import com.internode_studios.spring_boot_application.role.model.Role;
import com.internode_studios.spring_boot_application.role.repository.RoleRepository;
import com.internode_studios.spring_boot_application.user.model.User;
import com.internode_studios.spring_boot_application.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OtpService otpService;

    // Check if an admin user exists
    public boolean isAdminExists() {
        List<User> admins = userRepository.findByRole("admin");
        return !admins.isEmpty();
    }

    // Register or login admin user
    public User registerOrLoginAdmin(User user) {
        if (!isAdminExists()) {
            // First admin registration - no token required
            user.setRole("admin");  // Set the role as a string
            user.setIsOtpVerified(false);
            userRepository.save(user);
            otpService.generateOtp(user.getMobileNumber());
            return user;
        } else {
            // Existing admin login
            List<User> users = userRepository.findByMobileNumber(user.getMobileNumber());
            if (users.isEmpty()) {
                throw new RuntimeException("Admin user not found.");
            }

            User existingUser = users.get(0);
            if (!"admin".equalsIgnoreCase(existingUser.getRole())) {
                throw new RuntimeException("Invalid role for admin login.");
            }
            return existingUser;
        }
    }

    // Register or login non-admin user (only updates OTP if user already exists)
    public User registerOrLoginNonAdmin(User user) {
        // Initially set role to null for non-admin users
        user.setRole(null);

        // Find existing users by mobile number
        List<User> existingUsers = userRepository.findByMobileNumber(user.getMobileNumber());
        if (existingUsers.isEmpty()) {
            // New user registration
            user.setIsOtpVerified(false);
            userRepository.save(user);
            otpService.generateOtp(user.getMobileNumber());
            return user;
        } else {
            // If user exists, only update the OTP and save
            User existingUser = existingUsers.get(0);
            otpService.updateOtp(existingUser);  // Update OTP only
            return existingUser;
        }
    }

    // Check if the user has an admin role
    public boolean isAdminRole(User user) {
        return "admin".equalsIgnoreCase(user.getRole());
    }

    // Assign role to user
    public void assignRoleToUser(String roleName, Long userId) {
        // Check if the role exists in the roles table
        Optional<Role> role = roleRepository.findByName(roleName);
        if (role.isEmpty()) {
            throw new RuntimeException("Role '" + roleName + "' does not exist.");
        }

        // Find the user by ID
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User with ID " + userId + " not found.");
        }

        // Assign the role to the user
        User user = userOpt.get();
        user.setRole(roleName);
        userRepository.save(user);
    }

    // Verify OTP and generate token with id, role, and mobileNumber
    public String verifyOtp(String mobileNumber, String otp, JwtUtil jwtUtil) {
        if (otpService.validateOtp(mobileNumber, otp)) {
            List<User> users = userRepository.findByMobileNumber(mobileNumber);

            if (!users.isEmpty()) {
                User user = users.get(0);
                user.setIsOtpVerified(true);
                userRepository.save(user);

                return jwtUtil.generateToken(user.getId(), user.getRole(), user.getMobileNumber());
            }
        }
        return null;
    }

    // Method to get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
