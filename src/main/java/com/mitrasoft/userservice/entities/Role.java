package com.mitrasoft.userservice.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="role")
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users;

    public Role() {}

    public Role(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
