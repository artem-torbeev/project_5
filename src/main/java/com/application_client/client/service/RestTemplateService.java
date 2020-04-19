package com.application_client.client.service;

import com.application_client.client.model.UserDto;
import com.application_client.client.model.UserJwt;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class RestTemplateService {

    final private String URL_ID = "http://localhost:8085/admin/ ";

    RestTemplate restTemplate = new RestTemplate();

    private String authToken = "";

    public ResponseEntity<UserJwt> signIn() {
        final String url = "http://localhost:8085/login";

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("NAME_ONE " + auth);

        UserJwt userJwt = new UserJwt("admin@com", "000", null);
        UserJwt response = restTemplate.postForObject(url, userJwt, UserJwt.class);

        if (response != null) {
            authToken = (String) response.getModel().get("token");
//        System.out.println("token =" + authToken);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public List<UserDto> getAllUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("NAME_ONE " + auth);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + authToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        final String URL_ALL = "http://localhost:8085/admin/all";
        ResponseEntity<List<UserDto>> response = restTemplate.exchange(URL_ALL, HttpMethod.GET,
                entity, new ParameterizedTypeReference<List<UserDto>>() {
                });
        return response.getBody();
    }

    public UserDto createUser(UserDto user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + authToken);
        //todo передаем
        HttpEntity<UserDto> entity = new HttpEntity<>(user, headers);
        //todo получаем
        final String URL_CREATE = "http://localhost:8085/admin/create";
        ResponseEntity<UserDto> response = restTemplate
                .exchange(URL_CREATE, HttpMethod.POST, entity, UserDto.class);
        return response.getBody();
    }

    public UserDto updateUser(UserDto user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + authToken);

        HttpEntity<UserDto> entity = new HttpEntity<>(user, headers);
        final String URL_EDIT = "http://localhost:8085/admin/edit";
        ResponseEntity<UserDto> response = restTemplate
                .exchange(URL_EDIT, HttpMethod.PUT, entity, UserDto.class);
        return response.getBody();
    }

    public UserDto getUser(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + authToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<UserDto> response = restTemplate
                .exchange(URL_ID + id, HttpMethod.GET, entity, UserDto.class);
        return response.getBody();
    }

    public void deleteUser(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + authToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(URL_ID + id, HttpMethod.DELETE, entity, UserDto.class);
    }
}
