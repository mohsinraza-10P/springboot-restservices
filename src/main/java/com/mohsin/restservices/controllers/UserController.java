package com.mohsin.restservices.controllers;

import com.mohsin.restservices.entities.User;
import com.mohsin.restservices.exceptions.UserExistsException;
import com.mohsin.restservices.exceptions.UserNotFoundException;
import com.mohsin.restservices.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /*
    *
        @PostMapping("/users")
        public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) {
            try {
                userService.createUser(user);
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
                return new ResponseEntity<Void>(httpHeaders, HttpStatus.CREATED);
            } catch (UserExistsException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }
     */

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        try {
            User response = userService.createUser(user);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (UserExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public User updateUserById( @PathVariable("id") Long id, @RequestBody User user) {
        try {
            return userService.updateUserById(id, user);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/users/byusername/{username}")
    public User getUserByUsername(@PathVariable("username") String username) throws UserNotFoundException {
        return userService.getUserByUsername(username);
    }
}
