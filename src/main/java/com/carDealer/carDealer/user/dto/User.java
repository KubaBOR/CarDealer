package com.carDealer.carDealer.user.dto;

import com.carDealer.carDealer.user.model.RoleDocument;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<RoleDocument> roles;

    public User() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDocument> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDocument> roles) {
        this.roles = roles;
    }
}
