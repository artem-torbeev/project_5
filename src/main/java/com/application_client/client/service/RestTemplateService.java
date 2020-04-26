package com.application_client.client.service;

import com.application_client.client.model.UserDto;
import com.application_client.client.model.UserJwt;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class RestTemplateService {

    final RestTemplate restTemplate;
    final private String URL = "http://localhost:8085/admin/";

    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<UserJwt> signIn(UserJwt user) {
        final String URL_LOGIN = "http://localhost:8085/login";
        UserJwt response = restTemplate.postForObject(URL_LOGIN, user, UserJwt.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public List<UserDto> getAllUser(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<UserDto>> response = restTemplate.exchange(URL + "all", HttpMethod.GET,
                entity, new ParameterizedTypeReference<List<UserDto>>() {
                });
        return response.getBody();
    }

    public UserDto createUser(UserDto user, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + token);
        // передаем
        HttpEntity<UserDto> entity = new HttpEntity<>(user, headers);
        //получаем
        ResponseEntity<UserDto> response = restTemplate
                .exchange(URL + "create", HttpMethod.POST, entity, UserDto.class);
        return response.getBody();
    }

    public UserDto updateUser(UserDto user, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + token);

        HttpEntity<UserDto> entity = new HttpEntity<>(user, headers);
        ResponseEntity<UserDto> response = restTemplate
                .exchange(URL + "edit", HttpMethod.PUT, entity, UserDto.class);
        return response.getBody();
    }

    public UserDto getUser(Long id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<UserDto> response = restTemplate
                .exchange(URL + id, HttpMethod.GET, entity, UserDto.class);
        return response.getBody();
    }

    public void deleteUser(Long id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(URL + id, HttpMethod.DELETE, entity, UserDto.class);
    }
}

