package com.internode_studios.spring_boot_application.User.service.impl;

import com.internode_studios.spring_boot_application.User.dto.UserDto;
import com.internode_studios.spring_boot_application.User.entity.RoleConverter;
import com.internode_studios.spring_boot_application.User.entity.User;
import com.internode_studios.spring_boot_application.User.exception.ResourceNotFoundException;
import com.internode_studios.spring_boot_application.User.mapper.UserMapper;
import com.internode_studios.spring_boot_application.User.repository.UserRepository;
import com.internode_studios.spring_boot_application.User.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User is not exist with given Ud : " + userId));

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updateUser) {

        User user = userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User is not exists with given id : " + userId)
                );

        user.setFirst_name(updateUser.getFirst_name());
        user.setLast_name(updateUser.getLast_name());
        user.setPassword(updateUser.getPassword());
        user.setDob(updateUser.getDob());
        // Convert the role from string to Role enum
        user.setRole(RoleConverter.convertToRole(updateUser.getRole()));
        user.setPhone_number(updateUser.getPhone_number());
        user.setCell_phone_number(updateUser.getCell_phone_number());
        user.setProfile_picture(updateUser.getProfile_picture());
        user.setStatus(updateUser.getStatus());

        User updateUserObj = userRepository.save(user);

        return UserMapper.mapToUserDto(updateUserObj);
    }

    @Override
    public void deleteUser(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User is not exists with given id : " + userId)
        );

        userRepository.deleteById(userId);
    }

    @Override
    public UserDto loginUser(String username, String password){
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (user.getPassword().equals(password)) {
                return UserMapper.mapToUserDto(user);
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
