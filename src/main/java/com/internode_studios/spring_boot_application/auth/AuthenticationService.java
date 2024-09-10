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

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .first_name(request.getFirst_name())
                .last_name(request.getLast_name())
                .username(request.getUsername())
                .email_address(request.getEmail_address())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER) // Default role
                .dob(request.getDob())
                .phone_number(request.getPhone_number())
                .cell_phone_number(request.getCell_phone_number())
                .profile_picture(request.getProfile_picture())
                .status(request.getStatus())
                .build();
        User savedUser = repository.save(user);
        String jwtToken = jwtService.generateToken(user.getId().toString(), user.getUsername(), user.getRole().name());
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
