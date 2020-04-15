package com.application_client.client.restController;

import com.application_client.client.model.User;
import com.application_client.client.model.UserForm;
import com.application_client.client.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginRestController {
//
//    @Autowired
//    RestTemplateService restTemplateService;
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @ResponseBody
//    @PostMapping("/login")
//    public ResponseEntity<User> signIn(@RequestBody UserForm user) {
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(restTemplateService.signIn(user), HttpStatus.OK);
//    }
}
