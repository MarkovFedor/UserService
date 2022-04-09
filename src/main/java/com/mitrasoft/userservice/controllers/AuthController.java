package com.mitrasoft.userservice.controllers;

import com.mitrasoft.userservice.DtoMapping.UserDtoMapping;
import com.mitrasoft.userservice.dto.UserDTO;
import com.mitrasoft.userservice.entities.User;
import com.mitrasoft.userservice.exceptions.UsernameNotUniqueException;
import com.mitrasoft.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDtoMapping userDtoMapping;

    @PostMapping(value = "/register")
    public ResponseEntity registerNewUser(@RequestBody UserDTO userDTO) {
        User user = userDtoMapping.mapToUserEntity(userDTO);
        Long id = null;
        try {
            id = userService.register(user);
        } catch(UsernameNotUniqueException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(id, HttpStatus.CREATED);
    }
}
