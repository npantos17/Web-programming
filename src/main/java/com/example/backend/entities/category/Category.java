package com.example.backend.entities.category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Category {

    private int id;
    @NotNull(message = "Title category is required")
    @NotEmpty(message = "Title category is required")
    private String name;
    @NotNull(message = "Title category is required")
    @NotEmpty(message = "Title category is required")
    private String description;

    public Category(){

    }

    public Category(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
