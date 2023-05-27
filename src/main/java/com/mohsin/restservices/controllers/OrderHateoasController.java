package com.mohsin.restservices.controllers;

import com.mohsin.restservices.entities.Order;
import com.mohsin.restservices.entities.User;
import com.mohsin.restservices.exceptions.UserNotFoundException;
import com.mohsin.restservices.repositories.OrderRepository;
import com.mohsin.restservices.repositories.UserRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hateoas/orders")
public class OrderHateoasController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/byuser/{userId}")
    public CollectionModel<Order> getAllOrders(@PathVariable("userId") Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not present");
        }

        List<Order> orders = user.get().getOrders();
        return CollectionModel.of(orders);
    }
}
