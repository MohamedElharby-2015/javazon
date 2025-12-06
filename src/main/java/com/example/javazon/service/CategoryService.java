package com.example.javazon.service;

import com.example.javazon.entities.Category;
import com.example.javazon.entities.dtos.CategoryDto;
import com.example.javazon.repository.CategoryRepository;
import com.example.javazon.service.mappers.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;


    public CategoryDto addCategory(CategoryDto categorydto) {
        log.info("Adding Category: {}" , categorydto.getCategoryId());
        if (categoryRepository.existsByCategoryNameNative(categorydto.getCategoryName())==1){
            throw new RuntimeException("Category Name Already Existed");
        }

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
        if (!categoryRepository.existsById(id)){
            throw new RuntimeException("category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
