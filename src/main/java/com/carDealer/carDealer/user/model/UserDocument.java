package com.carDealer.carDealer.user.model;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "user")
public class UserDocument {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<RoleDocument> roles;

    public UserDocument() {
    }

    public UserDocument(String firstName, String lastName, String email, String password, Set<RoleDocument> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

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