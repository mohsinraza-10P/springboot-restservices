package com.mohsin.restservices.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.mohsin.restservices.entities.User;
import com.mohsin.restservices.exceptions.UserNotFoundException;
import com.mohsin.restservices.services.UserService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@Validated
@RequestMapping(value = "/jackson-filter/users")
public class UserMappingJacksonController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> userOptional = userService.getUserById(id);
        User user = userOptional.get();

        Set<String> fields = new HashSet<>();
        fields.add("id");
        fields.add("username");
        fields.add("email");

        return getMapper(fields, user);
    }

    @GetMapping("/params/{id}")
    public MappingJacksonValue getUserById(
            @PathVariable("id") @Min(1) Long id,
            @RequestParam("fields") Set<String> fields
    ) throws UserNotFoundException {
        Optional<User> userOptional = userService.getUserById(id);
        User user = userOptional.get();
        return getMapper(fields, user);
    }

    private MappingJacksonValue getMapper(Set<String> fields, User user) {
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
        MappingJacksonValue mapper = new MappingJacksonValue(user);
        mapper.setFilters(filterProvider);
        return mapper;
    }
}
