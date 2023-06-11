package com.mohsin.restservices.controllers;

import com.mohsin.restservices.dtos.UserDtoV1;
import com.mohsin.restservices.dtos.UserDtoV2;
import com.mohsin.restservices.entities.User;
import com.mohsin.restservices.exceptions.UserNotFoundException;
import com.mohsin.restservices.services.UserService;
import jakarta.validation.constraints.Min;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/versioning-uri/users")
public class UserUriVersioningController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping({"/v1/{id}", "/v1.1/{id}"})
    public UserDtoV1 getUserByIdV1(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> userOptional = userService.getUserById(id);
        User user = userOptional.get();
        return modelMapper.map(user, UserDtoV1.class);
    }

    @GetMapping("/v2/{id}")
    public UserDtoV2 getUserByIdV2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> userOptional = userService.getUserById(id);
        User user = userOptional.get();
        return modelMapper.map(user, UserDtoV2.class);
    }
}
