package com.mohsin.restservices.dtos;

import com.mohsin.restservices.entities.Order;

import java.util.List;

public class UserDtoV2 extends UserDtoV1 {

    private String gender;

    public UserDtoV2() {
        super();
    }

    public UserDtoV2(Long id, String username, String firstName, String lastName, String email, String role, String ssn, List<Order> orders, String gender) {
        super(id, username, firstName, lastName, email, role, ssn, orders);
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
