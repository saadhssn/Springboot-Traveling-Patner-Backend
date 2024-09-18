package com.internode_studios.spring_boot_application.auth;

import com.internode_studios.spring_boot_application.User.dto.UserDto;
import com.internode_studios.spring_boot_application.User.entity.Role;
import com.internode_studios.spring_boot_application.User.entity.User;
import com.internode_studios.spring_boot_application.User.mapper.UserMapper;
import com.internode_studios.spring_boot_application.User.repository.UserRepository;
import com.internode_studios.spring_boot_application.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // Generate a 6-digit OTP
    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generates a 6-digit OTP
        return String.valueOf(otp);
    }

    public AuthenticationResponse register(RegisterRequest request) {
        // Generate OTP
        String otp = generateOtp();

        // Build the User entity with OTP
        User user = User.builder()
                .first_name(request.getFirst_name())
                .last_name(request.getLast_name())
                .username(request.getUsername())
                .email_address(request.getEmail_address())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole())) // Convert string role to enum
                .dob(request.getDob())
                .phone_number(request.getPhone_number())
                .cell_phone_number(request.getCell_phone_number())
                .profile_picture(request.getProfile_picture())
                .status(request.getStatus())
                .otp(otp) // Save the generated OTP
                .build();

        // Save the user with the OTP
        User savedUser = repository.save(user);

        // Generate JWT token
        String jwtToken = jwtService.generateToken(savedUser.getId().toString(), savedUser.getUsername(), savedUser.getRole().name());

        // Map the saved user to UserDto
        UserDto userDto = UserMapper.mapToUserDto(savedUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(userDto)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user.getId().toString(), user.getUsername(), user.getRole().name());
        UserDto userDto = UserMapper.mapToUserDto(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(userDto)
                .build();
    }
}
