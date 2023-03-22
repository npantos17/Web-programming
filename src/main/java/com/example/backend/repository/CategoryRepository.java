package com.example.backend.repository;

import com.example.backend.entities.category.Category;

import java.util.List;

public interface CategoryRepository {

    public void deleteCategory(Integer id);
    public Category addCategory(Category category);
    public Category updateCategory(Category category);
    public Category findCategory(Integer id);
    public Category findCategoryByName(String name);
    public List<Category> allCategories();
    public List<Category> categoriesByPage(Integer pageNum);
}
