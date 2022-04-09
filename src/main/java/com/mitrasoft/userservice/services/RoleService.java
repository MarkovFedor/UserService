package com.mitrasoft.userservice.services;

import com.mitrasoft.userservice.entities.Role;
import com.mitrasoft.userservice.exceptions.NotFoundByIdException;
import com.mitrasoft.userservice.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleById(Long id) throws NotFoundByIdException {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isEmpty()) {
            throw new NotFoundByIdException("Role with this id not exists");
        }
        return role.get();
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    public void addRole(Long id, String name) {
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        roleRepository.save(role);
    }
}
