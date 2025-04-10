package com.internode_studios.spring_boot_application.user.controller;

import com.internode_studios.spring_boot_application.Jwt.service.JwtUtil;
import com.internode_studios.spring_boot_application.user.dto.UserDTO;
import com.internode_studios.spring_boot_application.user.model.User;
import com.internode_studios.spring_boot_application.user.repository.UserRepository;
import com.internode_studios.spring_boot_application.user.service.OtpService;
import com.internode_studios.spring_boot_application.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

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
                if (token == null) {
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

//    @PostMapping("/user/login")
//    public ResponseEntity<?> userLogin(@RequestBody User user) {
//        try {
//            // Restrict admins from using the user login endpoint
//            if (userService.isAdminRole(user)) {
//                return ResponseEntity.status(403).body("Admin login is restricted to /admin/login endpoint.");
//            }
//
//            // Proceed with user registration or login
//            User nonAdminUser = userService.registerOrLoginNonAdmin(user);
//            return ResponseEntity.ok(nonAdminUser);
//
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(400).body(e.getMessage());
//        }
//    }

    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(@RequestBody User userRequest) {
        try {
            // Restrict admin login on this endpoint
            if (userService.isAdminRole(userRequest)) {
                return ResponseEntity.status(403).body("Admin login is restricted to /admin/login endpoint.");
            }

            // Register or fetch existing user
            Map<String, Object> response = userService.registerOrLoginNonAdmin(userRequest);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody User userRequest) {
        Map<String, Object> response = userService.verifyOtp(userRequest.getMobileNumber(), userRequest.getOtp());
        if (response != null) {
            return ResponseEntity.ok(response);
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

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(c -> ResponseEntity.ok((Object) c)) // Cast user object to Object
                .orElseGet(() -> ResponseEntity.status(404).body("User not found"));
    }

    @PostMapping("/setpassword")
    public ResponseEntity<?> setPassword(@RequestBody Map<String, Object> payload) {
        try {
            Long userId = ((Number) payload.get("userId")).longValue();
            String password = (String) payload.get("password");
            String confirmPassword = (String) payload.get("confirmPassword");

            String result = userService.setPassword(userId, password, confirmPassword);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/verify-password")
    public ResponseEntity<?> verifyPassword(@RequestBody User userRequest) {
        try {
            Map<String, Object> response = userService.verifyPassword(userRequest.getMobileNumber(), userRequest.getPassword());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/sales-agent")
    public ResponseEntity<?> addSalesAgent(@RequestBody UserDTO userDTO) {
        try {
            User newAgent = userService.addSalesAgent(userDTO);
            return ResponseEntity.ok(newAgent);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/sales-agent/{userId}")
    public ResponseEntity<?> updateSalesAgent(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        try {
            User updatedAgent = userService.updateSalesAgent(userId, userDTO);
            return ResponseEntity.ok(updatedAgent);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/user/soft-delete/{id}")
    public ResponseEntity<?> softDeleteUser(@PathVariable Long id) {
        System.out.println("Soft delete called for user ID: " + id); // Add this line
        try {
            userService.softDeleteUser(id);
            return ResponseEntity.ok("User soft deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/user/hard-delete/{id}")
    public ResponseEntity<?> hardDeleteUser(@PathVariable Long id) {
        try {
            userService.hardDeleteUser(id);
            return ResponseEntity.ok("User hard deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/getDetails/{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable Long userId) {
        try {
            Map<String, Object> userDetails = userService.getUserDetails(userId);
            return ResponseEntity.ok(userDetails);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
