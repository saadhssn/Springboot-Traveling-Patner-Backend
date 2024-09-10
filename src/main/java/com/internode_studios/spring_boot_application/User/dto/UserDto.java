package com.internode_studios.spring_boot_application.User.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class UserDto {

    private Long id;

    @NotBlank(message = "first_name is required")
    private String first_name;

    @NotBlank(message = "last_name is required")
    private String last_name;

    @NotBlank(message = "username is required")
    @Size(min = 3, max = 15, message = "username should be between 3 and 15 characters")
    private String username;

    @NotBlank(message = "password is required")
    @Size(min = 3, max = 15, message = "password should be between 3 and 15 characters")
    private String password;

    @Email(message = "email_address should be valid")
    @NotBlank(message = "email_address is required")
    private String email_address;

    @Past(message = "Date of birth should be in the past")
    private Date dob; // Changed from java.sql.Date to java.util.Date

    @NotBlank(message = "role is required")
    private String role;

    private String phone_number;
    private String cell_phone_number;
    private String profile_picture;
    private Boolean status;

    // Additional constructor for login functionality
    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
