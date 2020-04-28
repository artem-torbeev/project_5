package com.application_client.client.restController;

import com.application_client.client.model.UserDto;
import com.application_client.client.service.RestTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    final RestTemplateService restTemplateService;

    public AdminRestController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> showAllUser(@CookieValue(value = "token", defaultValue = "not defined") String token) {
        return new ResponseEntity<>(restTemplateService.getAllUser(token), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserDto user,
                                             @CookieValue(value = "token", defaultValue = "not defined") String token) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(restTemplateService.createUser(user, token), HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> editUser(@RequestBody UserDto user,
                                           @CookieValue(value = "token", defaultValue = "not defined") String token) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(restTemplateService.updateUser(user, token), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long id,
                                          @CookieValue(value = "token", defaultValue = "not defined") String token) {
        return new ResponseEntity<>(restTemplateService.getUser(id, token), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id,
                                             @CookieValue(value = "token", defaultValue = "not defined") String token) {
        restTemplateService.deleteUser(id, token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

