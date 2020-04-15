package com.application_client.client.service;

import com.application_client.client.model.User;
import com.application_client.client.model.UserForm;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class RestTemplateService {

    RestTemplate restTemplate = new RestTemplate();

//    public User signIn(UserForm user) {
//        final String url = "http://localhost:8085/login";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        HttpEntity<UserForm> entity = new HttpEntity<>(user, headers);
//
//        ResponseEntity<User> response = restTemplate
//                .exchange(url, HttpMethod.POST, entity, User.class);
//        return response.getBody();
//    }

    public List<User> getAllUser() {
        final String url = "http://localhost:8085/admin/all";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List<User>> response = restTemplate.exchange(url, HttpMethod.GET,
                entity, new ParameterizedTypeReference<List<User>>() {});
        return response.getBody();
    }

    public User createUser(User user) {
        final String url = "http://localhost:8085/admin/create";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<User> response = restTemplate
                .exchange(url, HttpMethod.POST, entity, User.class);
        return response.getBody();
    }

    public User updateUser(User user) {
        final String url = "http://localhost:8085/admin/edit";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<User> response = restTemplate
                .exchange(url, HttpMethod.PUT, entity, User.class);
        return response.getBody();
    }

    public User getUser(Long id) {
        final String url = "http://localhost:8085/admin/ " + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(headers);

        ResponseEntity<User> response = restTemplate
                .exchange(url, HttpMethod.GET, entity, User.class);
        return response.getBody();
    }

    public void deleteUser(Long id) {
        final String url = "http://localhost:8085/admin/ " + id;
        restTemplate.delete(url);
    }
}
