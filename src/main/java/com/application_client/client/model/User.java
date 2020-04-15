package com.application_client.client.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
public class User implements UserDetails {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Set<Role> role = new HashSet<>();
    private Set<Role> authorities = new HashSet<>();
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

/*    {
        "id": 38,
        "username": "admin",
        "email": "admin@com",
        "password": "$2a$10$ZM.iZVJE7oOF6tvvwWgP5ehPoFSk6pcEupKrwmDgufCWLfpfPc7ku",
        "role": [
            {
                "id": 2,
                "role": "ROLE_ADMIN",
                "authority": "ROLE_ADMIN"
            }
        ],
        "authorities": [
            {
                "id": 2,
                "role": "ROLE_ADMIN",
                "authority": "ROLE_ADMIN"
            }
        ],
        "accountNonExpired": true,
        "accountNonLocked": true,
        "credentialsNonExpired": true,
        "enabled": true
    },*/
