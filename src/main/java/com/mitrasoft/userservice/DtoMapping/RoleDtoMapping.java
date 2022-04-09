package com.mitrasoft.userservice.DtoMapping;

import com.mitrasoft.userservice.dto.RoleDTO;
import com.mitrasoft.userservice.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleDtoMapping {
    public Role mapToRoleEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        role.setId(roleDTO.getId());
        return role;
    }
}
