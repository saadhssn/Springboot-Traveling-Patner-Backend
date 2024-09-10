package com.internode_studios.spring_boot_application.User.entity;
import com.internode_studios.spring_boot_application.User.entity.Role;


public class RoleConverter {
    public static Role convertToRole(String roleString) {
        try {
            return Role.valueOf(roleString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + roleString);
        }
    }
}
