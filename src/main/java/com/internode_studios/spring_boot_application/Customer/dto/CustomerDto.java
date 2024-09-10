package com.internode_studios.spring_boot_application.Customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long id;

    @NotBlank(message = "First name is required")
    private String first_name;

    @NotBlank(message = "Last name is required")
    private String last_name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email_address;

    @Past(message = "Date of birth should be in the past")
    private Date dob;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 30, message = "Username should be between 3 and 30 characters")
    private String username;
}
