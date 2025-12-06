package com.example.javazon.service;

import com.example.javazon.entities.Category;
import com.example.javazon.entities.dtos.CategoryDto;
import com.example.javazon.repository.CategoryRepository;
import com.example.javazon.service.mappers.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;


    public CategoryDto addCategory(CategoryDto categorydto) {
        Category category = categoryMapper.toEntity(categorydto);
        category.setActive(true);
        categoryRepository.save(category);
        return categoryMapper.toDto(category);

    }

    public List<CategoryDto> getAllCategories() {
        return categoryMapper.toDto(categoryRepository.findAll());
    }


    public CategoryDto getCategoryById(int id) {

      Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        return categoryMapper.toDto(category);
    }


    public CategoryDto updateCategory(int id, CategoryDto updatedCategory) {
     Category currentCategory =categoryRepository.findById(id).orElseThrow(
             ()->new RuntimeException("Category not found with id:  " + id));

     categoryMapper.updateEntityFromDto(currentCategory,updatedCategory);
     categoryRepository.save(currentCategory);

     return categoryMapper.toDto(currentCategory);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}
