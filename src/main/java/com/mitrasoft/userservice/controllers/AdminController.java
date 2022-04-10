package com.mitrasoft.userservice.controllers;

import com.mitrasoft.userservice.DtoMapping.RoleDtoMapping;
import com.mitrasoft.userservice.dto.RoleDTO;
import com.mitrasoft.userservice.dto.UserDTO;
import com.mitrasoft.userservice.entities.Role;
import com.mitrasoft.userservice.entities.User;
import com.mitrasoft.userservice.services.RoleService;
import com.mitrasoft.userservice.services.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleDtoMapping roleDtoMapping;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private RestTemplate restTemplate;

    public AdminController() {
        restTemplate = new RestTemplate();
    }
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
    public ResponseEntity getInformation(Authentication authentication) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.loadUserByUsername(auth.getName());
        return new ResponseEntity(user.getRoles(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getAllUsersFromAdminMicroservice(
            @RequestHeader Map<String, String> requestHeaders
    ) {
        String plainCreds = requestHeaders.get("authorization");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", plainCreds);
        HttpEntity<String> request = new HttpEntity<String>(headers);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/adminservice/getallusers";
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, request, List.class);
        return response;
    }
}
