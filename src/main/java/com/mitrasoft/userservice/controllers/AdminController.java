package com.mitrasoft.userservice.controllers;

import com.mitrasoft.userservice.DtoMapping.RoleDtoMapping;
import com.mitrasoft.userservice.dto.RoleDTO;
import com.mitrasoft.userservice.entities.Role;
import com.mitrasoft.userservice.services.RoleService;
import com.mitrasoft.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(name = "/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleDtoMapping roleDtoMapping;

    @DeleteMapping(name = "/delete/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(name="/create/role")
    public ResponseEntity createRole(@RequestBody RoleDTO roleDTO) {
        Role role = roleDtoMapping.mapToRoleEntity(roleDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(name = "/delete/role/{id}")
    public ResponseEntity deleteRoleById(@PathVariable Long id) {
        roleService.deleteRole(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
