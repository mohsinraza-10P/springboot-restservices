package com.mohsin.restservices.controllers;

import com.mohsin.restservices.dtos.UserMsDto;
import com.mohsin.restservices.entities.User;
import com.mohsin.restservices.mappers.UserMapper;
import com.mohsin.restservices.repositories.UserRepository;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/map-struct/users")
public class UserMapStructController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @GetMapping()
    public List<UserMsDto> getAllUsers() {
        return userMapper.usersToUserMsDtoList(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public UserMsDto getUserById(@PathVariable("id") @Min(1) Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        return userMapper.userToUserMsDto(user);
    }
}
