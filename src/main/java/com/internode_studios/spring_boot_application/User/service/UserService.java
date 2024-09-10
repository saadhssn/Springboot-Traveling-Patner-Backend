package com.internode_studios.spring_boot_application.User.service;

import com.internode_studios.spring_boot_application.User.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUser();

    UserDto updateUser(Long userId, UserDto updateUser);

    void deleteUser(Long userId);

    UserDto loginUser(String username, String password);

}
