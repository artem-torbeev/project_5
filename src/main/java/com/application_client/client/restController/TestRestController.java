package com.application_client.client.restController;

import com.application_client.client.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @Autowired
    RestTemplateService restTemplateService;

    @PostMapping("/test")
    public ResponseEntity testUser() {
        return ResponseEntity.ok(restTemplateService.signIn());
    }
}
