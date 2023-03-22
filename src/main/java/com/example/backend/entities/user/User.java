package com.example.backend.entities.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {

    private int id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String email;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String name;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String lastName;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String status;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String password;
    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String type;

    public User(){

    }

    public User(int id, String email, String name, String lastName, String status, String password, String type){
        this.id = id;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.status = status;
        this.password = password;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

}

