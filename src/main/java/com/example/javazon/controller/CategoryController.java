package com.example.javazon.controller;

import com.example.javazon.entities.dtos.CategoryDto;
import com.example.javazon.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @GetMapping("/getAllCategories")
    public List<CategoryDto>getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("getCategoryById/{id}")
    public CategoryDto getCategoryById(@PathVariable int id){
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
        return "deleted Successfully";

    }



}
