package com.internode_studios.spring_boot_application.role.controller;


import com.internode_studios.spring_boot_application.role.model.Role;
import com.internode_studios.spring_boot_application.role.service.RoleService;
import com.internode_studios.spring_boot_application.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        Role createRole = roleService.createRole(role);
        return ResponseEntity.ok(createRole);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    // Get role by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        return role.map(r -> ResponseEntity.ok((Object) r)) // Cast to Object for compatibility
                .orElseGet(() -> ResponseEntity.status(404).body("Role not found"));
    }


    // Update a role
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody Role roleDetails) {
        Role updatedRole = roleService.updateRole(id, roleDetails);
        if (updatedRole != null) {
            return ResponseEntity.ok(updatedRole);
        }
        return ResponseEntity.status(404).body("Role not found");
    }

    // Delete a role
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        if (role.isPresent()) {
            roleService.deleteRole(id);
            return ResponseEntity.ok("Role deleted successfully");
        }
        return ResponseEntity.status(404).body("Role not found");
    }
}
