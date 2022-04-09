package com.mitrasoft.userservice.controllers;

import com.mitrasoft.userservice.DtoMapping.RoleDtoMapping;
import com.mitrasoft.userservice.dto.RoleDTO;
import com.mitrasoft.userservice.entities.Role;
import com.mitrasoft.userservice.entities.User;
import com.mitrasoft.userservice.services.RoleService;
import com.mitrasoft.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleDtoMapping roleDtoMapping;

    @PostMapping("/delete/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/create/role")
    public ResponseEntity createRole(@RequestBody RoleDTO roleDTO) {
        Role role = roleDtoMapping.mapToRoleEntity(roleDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/delete/role/{id}")
    public ResponseEntity deleteRoleById(@PathVariable Long id) {
        roleService.deleteRole(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity getInformation(Principal principal) {
        User user = userService.loadUserByUsername(principal.getName());
        return new ResponseEntity(user.getRoles(), HttpStatus.OK);
    }
}
