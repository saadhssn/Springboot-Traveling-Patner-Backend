package com.internode_studios.spring_boot_application.User.mapper;

import com.internode_studios.spring_boot_application.User.dto.UserDto;
import com.internode_studios.spring_boot_application.User.entity.Role;
import com.internode_studios.spring_boot_application.User.entity.User;

import java.sql.Date;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail_address(),
                user.getDob() != null ? new Date(user.getDob().getTime()) : null, // Convert to java.sql.Date
                user.getRole() != null ? user.getRole().name() : null, // Convert enum to string
                user.getPhone_number(),
                user.getCell_phone_number(),
                user.getProfile_picture(),
                user.getStatus()
        );
    }

    public static User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getFirst_name(),
                userDto.getLast_name(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail_address(),
                userDto.getDob() != null ? new java.util.Date(userDto.getDob().getTime()) : null, // Convert to java.util.Date
                userDto.getRole() != null ? Role.valueOf(userDto.getRole()) : null, // Convert string to enum
                userDto.getPhone_number(),
                userDto.getCell_phone_number(),
                userDto.getProfile_picture(),
                userDto.getStatus()
        );
    }
}
