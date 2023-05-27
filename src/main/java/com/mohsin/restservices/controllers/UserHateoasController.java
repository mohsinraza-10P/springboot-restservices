package com.mohsin.restservices.controllers;

import com.mohsin.restservices.entities.Order;
import com.mohsin.restservices.entities.User;
import com.mohsin.restservices.exceptions.UserNotFoundException;
import com.mohsin.restservices.repositories.UserRepository;
import com.mohsin.restservices.services.UserService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/hateoas/users")
public class UserHateoasController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public CollectionModel<User> getAllUsers() throws UserNotFoundException {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            // Build self link for each user
            createSelfLink(user);
            // Build relationship link with orders
            createOrdersLink(user);
        }
        // Build self link for all user
        Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
        return CollectionModel.of(users, selfLink);
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
        Optional<User> optionalUser = userService.getUserById(id);
        User user = optionalUser.get();
        createSelfLink(user);
        return EntityModel.of(user);
    }

    private void createSelfLink(User user) {
        Long userId = user.getId();
        Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
        user.add(selfLink);
    }

    private static void createOrdersLink(User user) throws UserNotFoundException {
        CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(user.getId());
        Link relationLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
        user.add(relationLink);
    }
}
