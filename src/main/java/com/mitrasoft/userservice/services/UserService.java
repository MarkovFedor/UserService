package com.mitrasoft.userservice.services;

import com.mitrasoft.userservice.entities.User;
import com.mitrasoft.userservice.exceptions.UsernameNotUniqueException;
import com.mitrasoft.userservice.repositories.RoleRepository;
import com.mitrasoft.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User with this username not found");
        }
        return user;
    }

    public Long register(User user) throws UsernameNotUniqueException {
        boolean userExists = userRepository.existsByUsername(user.getUsername());
        if(userExists) {
            throw new UsernameNotUniqueException("Not unique username");
        } else {
            String userPassword = user.getPassword();
            user.setPassword(passwordEncoder.encode(userPassword));
            user.addRole(roleRepository.findById(2L).get());
        }
        userRepository.save(user);
        return user.getId();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
