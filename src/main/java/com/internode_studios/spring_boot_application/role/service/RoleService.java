package com.internode_studios.spring_boot_application.role.service;


import com.internode_studios.spring_boot_application.role.model.Role;
import com.internode_studios.spring_boot_application.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // Create Role
    public Role createRole(Role role) {
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

    // Update Role
    public Role updateRole(Long id, Role roleDetails) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            Role existingRole = role.get();
            existingRole.setName(roleDetails.getName());
            existingRole.setSlug(roleDetails.getSlug());
            return roleRepository.save(existingRole);
        }
        return null;
    }

    // Delete Role
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}