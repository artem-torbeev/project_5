package com.application_client.client.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Role implements GrantedAuthority {
    private Long id;
    private String role;
    private String authority;

    @Override
    public String getAuthority() {
        return role;
    }
}
