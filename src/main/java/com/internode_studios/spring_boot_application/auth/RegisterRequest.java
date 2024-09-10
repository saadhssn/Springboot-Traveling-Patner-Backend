package com.internode_studios.spring_boot_application.auth;

import com.internode_studios.spring_boot_application.User.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String first_name;
    private String last_name;
    private String username;
    private String email_address;
    private String password;
    private String role;
    private Date dob;
    private String phone_number;
    private String cell_phone_number;
    private String profile_picture;
    private Boolean status;
}

