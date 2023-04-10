package com.mohsin.restservices.controllers;

import com.mohsin.restservices.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/hello-bean")
    public User getUserInfo() {
        return new User("Mohsin", "Raza", "Karachi");
    }
}
