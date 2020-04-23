package com.application_client.client.service;

import com.application_client.client.model.UserDto;
import com.application_client.client.model.UserJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Service
public class RestTemplateService {
    final HttpSession session;
    final RestTemplate restTemplate;
    final private String URL = "http://localhost:8085/admin/";

    public RestTemplateService(HttpSession session, RestTemplate restTemplate) {
        this.session = session;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<UserJwt> signIn(UserJwt user) {

        UserJwt userJwt = new UserJwt(user.getEmail(), user.getPassword(), null);
        final String URL_LOGIN = "http://localhost:8085/login";
        UserJwt response = restTemplate.postForObject(URL_LOGIN, userJwt, UserJwt.class);
        //todo получить токен из ответа
        if (response != null) {
            String token = (String) response.getModel().get("token");
            session.setAttribute("token", token);
//            System.out.println ( "TOKEN === >>> " + session.getAttribute("token"));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public List<UserDto> getAllUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + session.getAttribute("token"));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<UserDto>> response = restTemplate.exchange(URL + "all", HttpMethod.GET,
                entity, new ParameterizedTypeReference<List<UserDto>>() {
                });
        return response.getBody();
    }

    public UserDto createUser(UserDto user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + session.getAttribute("token"));
        // передаем
        HttpEntity<UserDto> entity = new HttpEntity<>(user, headers);
        //получаем
        ResponseEntity<UserDto> response = restTemplate
                .exchange(URL + "create", HttpMethod.POST, entity, UserDto.class);
        return response.getBody();
    }

    public UserDto updateUser(UserDto user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + session.getAttribute("token"));

        HttpEntity<UserDto> entity = new HttpEntity<>(user, headers);
        ResponseEntity<UserDto> response = restTemplate
                .exchange(URL + "edit", HttpMethod.PUT, entity, UserDto.class);
        return response.getBody();
    }

    public UserDto getUser(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + session.getAttribute("token"));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<UserDto> response = restTemplate
                .exchange(URL + id, HttpMethod.GET, entity, UserDto.class);
        return response.getBody();
    }

    public void deleteUser(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + session.getAttribute("token"));

        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(URL + id, HttpMethod.DELETE, entity, UserDto.class);
    }
}

