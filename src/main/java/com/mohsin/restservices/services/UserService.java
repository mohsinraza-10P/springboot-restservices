package com.mohsin.restservices.services;

import com.mohsin.restservices.entities.User;
import com.mohsin.restservices.exceptions.UserExistsException;
import com.mohsin.restservices.exceptions.UserNotFoundException;
import com.mohsin.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) throws UserExistsException {
        User existingUser = getUserByUsername(user.getUsername());
        if (existingUser != null) {
            throw new UserExistsException("User already exists.");
        }
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User does not exist.");
        }
        return user;
    }

    public User updateUserById(Long id, User user) throws UserNotFoundException {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException("User not found, please provide correct user id.");
        }
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found, please provide correct user id.");
        }
        userRepository.deleteById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
