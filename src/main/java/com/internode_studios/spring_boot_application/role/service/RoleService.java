package com.internode_studios.spring_boot_application.role.service;


import com.internode_studios.spring_boot_application.role.model.Role;
import com.internode_studios.spring_boot_application.role.repository.RoleRepository;
import com.internode_studios.spring_boot_application.user.model.User;
import com.internode_studios.spring_boot_application.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Role with lowercase name and slug
    public Role createRole(Role role) {
        // Convert name and slug to lowercase
        role.setName(role.getName().toLowerCase());
        role.setSlug(role.getSlug().toLowerCase());
        return roleRepository.save(role);
    }

    // Get All Roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Get Role by ID
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    // Update Role with lowercase name and slug
    public Role updateRole(Long id, Role roleDetails) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            Role existingRole = role.get();

            // Update fields only if they are non-null
            if (roleDetails.getName() != null) {
                existingRole.setName(roleDetails.getName().toLowerCase());
            }
            if (roleDetails.getSlug() != null) {
                existingRole.setSlug(roleDetails.getSlug().toLowerCase());
            }

            // Save the updated role
            return roleRepository.save(existingRole);
        }

        // Handle case where the role is not found
        throw new RuntimeException("Role not found with ID: " + id);
    }


    // Delete Role
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

}
