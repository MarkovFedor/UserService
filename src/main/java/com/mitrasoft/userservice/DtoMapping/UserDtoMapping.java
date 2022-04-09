package com.mitrasoft.userservice.DtoMapping;

import com.mitrasoft.userservice.dto.UserDTO;
import com.mitrasoft.userservice.entities.User;
import com.mitrasoft.userservice.repositories.RoleRepository;
import com.mitrasoft.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDtoMapping {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setRoles(user.getRoles());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

    public User mapToUserEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
