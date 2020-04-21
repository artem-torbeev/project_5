package com.application_client.client.service;

import com.application_client.client.model.User;
import com.application_client.client.model.UserJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private RestTemplateService restTemplateService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private Map<String, User> roles = new HashMap<>();

    @PostConstruct
    public void init() {
        roles.put("admin@com", new User("admin@com", passwordEncoder.encode("000"), getAuthority("ROLE_ADMIN")));
        roles.put("seva@com", new User("seva@com", passwordEncoder.encode("111"), getAuthority("ROLE_USER")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (username == null) {
            throw new UsernameNotFoundException("User Not Found ");
        }
        if (roles.get(username)== null){
            throw new UsernameNotFoundException("User Not Found with -> username or email : " + null);
        }
        // todo получение пароляи имяни
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String name = request.getParameter("username"); // get from request username
        String password = request.getParameter("password"); // get from request parameter
        // todo получение токена
        restTemplateService.signIn(new UserJwt(name, password, null));

        return roles.get(username);
    }

    private List<GrantedAuthority> getAuthority(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
