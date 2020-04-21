package com.application_client.client.restController;

import com.application_client.client.model.User;
import com.application_client.client.model.UserJwt;
import com.application_client.client.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginRestController {

    @Autowired
    private RestTemplateService restTemplateService;

//    @GetMapping("login")
//    public String login() {
//        return "/login";
//    }

//    @PostMapping("/login")
//    public void signIn(@RequestBody UserJwt userJwt) {

//        Authentication users = SecurityContextHolder.getContext().getAuthentication();
//        String name = users.getName(); //get logged in username
//
//        String email = user.getEmail();
//        String password= user.getPassword();

//        restTemplateService.signIn(user);
//        return "redirect:/user";
//    }

//    @GetMapping("user")
//    public String home() {
//        return "/user";
//    }
}
