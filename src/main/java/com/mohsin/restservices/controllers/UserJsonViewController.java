package com.mohsin.restservices.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.mohsin.restservices.entities.User;
import com.mohsin.restservices.entities.Views;
import com.mohsin.restservices.exceptions.UserNotFoundException;
import com.mohsin.restservices.services.UserService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/json-view/users")
public class UserJsonViewController {

    @Autowired
    private UserService userService;

    @JsonView(Views.External.class)
    @GetMapping("/external/{id}")
    public Optional<User> getExternalUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @JsonView(Views.Internal.class)
    @GetMapping("/internal/{id}")
    public Optional<User> getInternalUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        return userService.getUserById(id);
    }
}
