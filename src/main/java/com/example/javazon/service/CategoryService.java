package com.example.javazon.service;

import com.example.javazon.entities.Category;
import com.example.javazon.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category updateCategory(int id, Category updatedCategory) {
        return categoryRepository.findById(id).map(category -> {

            category.setCategoryName(updatedCategory.getCategoryName());
            category.setCategoryDescription(updatedCategory.getCategoryDescription());
            category.setActive(updatedCategory.isActive());

            return categoryRepository.save(category);

        }).orElse(null);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
