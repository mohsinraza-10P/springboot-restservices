package com.mohsin.restservices.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Entity
@Table(name = "users")
//@JsonIgnoreProperties({"firstName", "lastName"}) - For Static Filtering @JsonIgnore
@JsonFilter(value = "userFilter") // - For Dynamic Filtering using MappingJacksonValue
public class User extends RepresentationModel<User> {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Username is required.")
    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Size(min = 2, message = "First name should have at least 2 characters.")
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email_address", length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 50, nullable = false)
    private String role;

    @Column(length = 50, nullable = false, unique = true)
    //@JsonIgnore - For Static Filtering @JsonIgnore
    private String ssn;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User() {
    }

    public User(
        Long id,
        @NotEmpty(message = "Username is required.") String username,
        @Size(min = 2, message = "First name should have at least 2 characters.") String firstName,
        String lastName, String email, String role, String ssn
    ) {
        super();
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSSN() {
        return ssn;
    }

    public void setSSN(String ssn) {
        this.ssn = ssn;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", ssn='" + ssn + '\'' +
                ", orders=" + orders +
                '}';
    }
}
