package com.mohsin.restservices.controllers;

import com.mohsin.restservices.models.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/hello-bean")
    public UserInfo getUserInfo() {
        return new UserInfo("Mohsin", "Raza", "Karachi");
    }
}
