package com.mohsin.restservices.controllers;

import com.mohsin.restservices.dtos.UserMmDto;
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
@RequestMapping(value = "/model-mapper/users")
public class UserModelMapperController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserMmDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> userOptional = userService.getUserById(id);
        User user = userOptional.get();
        return modelMapper.map(user, UserMmDto.class);
    }
}
