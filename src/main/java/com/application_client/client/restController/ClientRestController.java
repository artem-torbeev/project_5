package com.application_client.client.restController;

import com.application_client.client.model.UserDto;
import com.application_client.client.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ClientRestController {

    @Autowired
    RestTemplateService restTemplateService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> showAllUser() {
        return new ResponseEntity<>(restTemplateService.getAllUser(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserDto user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(restTemplateService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<Object> editUser(@RequestBody UserDto user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(restTemplateService.updateUser(user), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long id) {

        return new ResponseEntity<>(restTemplateService.getUser(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
       restTemplateService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

