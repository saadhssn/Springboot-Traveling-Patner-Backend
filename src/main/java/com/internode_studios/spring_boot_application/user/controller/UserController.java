package com.internode_studios.spring_boot_application.user.controller;

import com.internode_studios.spring_boot_application.Jwt.service.JwtUtil;
import com.internode_studios.spring_boot_application.user.model.User;
import com.internode_studios.spring_boot_application.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody User user, @RequestHeader(value = "Authorization", required = false) String token) {
        try {
            // Ensure an admin exists
            if (userService.isAdminExists()) {
                // Check if the Bearer token is present
                if (token == null || !token.startsWith("Bearer ")) {
                    return ResponseEntity.status(401).body("Bearer token is required for admin login.");
                }

                // Extract the JWT token and mobile number
                String jwt = token.substring(7);
                String mobileNumber = jwtUtil.extractMobileNumber(jwt);

                // Check if the token is valid
                if (!jwtUtil.validateToken(jwt, mobileNumber)) {
                    return ResponseEntity.status(401).body("Invalid Bearer token.");
                }

                // Extract the role from the token and ensure it is 'admin'
                String role = jwtUtil.extractRole(jwt);
                if (!"admin".equalsIgnoreCase(role)) {
                    return ResponseEntity.status(403).body("Only admin users are allowed to log in via /admin/login.");
                }
            }

            // Proceed with admin registration or login
            User adminUser = userService.registerOrLoginAdmin(user);
            return ResponseEntity.ok(adminUser);

        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(@RequestBody User user) {
        try {
            // Restrict admins from using the user login endpoint
            if (userService.isAdminRole(user)) {
                return ResponseEntity.status(403).body("Admin login is restricted to /admin/login endpoint.");
            }

            // Proceed with user registration or login
            User nonAdminUser = userService.registerOrLoginNonAdmin(user);
            return ResponseEntity.ok(nonAdminUser);

        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody User userRequest) {
        String token = userService.verifyOtp(userRequest.getMobileNumber(), userRequest.getOtp(), jwtUtil);
        if (token != null) {
            return ResponseEntity.ok("Bearer " + token);
        }
        return ResponseEntity.status(401).body("Invalid OTP.");
    }

    @PostMapping("/user/roleassign")
    public ResponseEntity<?> assignRole(@RequestBody Map<String, Object> requestData) {
        try {
            String role = (String) requestData.get("role");
            Long userId = ((Number) requestData.get("userId")).longValue();

            userService.assignRoleToUser(role, userId);
            return ResponseEntity.ok("Role assigned successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
