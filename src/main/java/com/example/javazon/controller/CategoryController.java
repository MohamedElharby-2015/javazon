package com.example.javazon.controller;

import com.example.javazon.entities.Category;
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
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
    @GetMapping("/getAllCategories")
    public List<Category>getAllCategories(){
        return categoryService.getAllCategories();
    }
    @GetMapping("getCategoryById/{id}")
    public Category getCategoryById(@PathVariable int id){
        return categoryService.getCategoryById(id);
    }
    @DeleteMapping("deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
        return "deleted Successfully";

    }



}
