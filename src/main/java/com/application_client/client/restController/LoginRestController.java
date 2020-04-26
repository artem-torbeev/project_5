package com.application_client.client.restController;

import com.application_client.client.model.UserJwt;
import com.application_client.client.service.RestTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class LoginRestController {

    private final RestTemplateService restTemplateService;

    public LoginRestController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("login")
    public String login() {
        return "/login";
    }

    @PostMapping("/login")
    public ResponseEntity<Object> signIn(@RequestBody UserJwt userJwt) {
        return new ResponseEntity<>(restTemplateService.signIn(userJwt), HttpStatus.OK);
    }
}
