package com.example.javazon.controller;

import com.example.javazon.entities.dtos.ProductDto;
import com.example.javazon.model.PagedResponse;
import com.example.javazon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/javazon/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @GetMapping("/all")
    public ResponseEntity<PagedResponse<ProductDto>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(productService.getAllProduct(page, size));
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable int id, @RequestBody ProductDto product) {
        return productService.updateProduct(id, product);
    }
    
    @PutMapping("/assignCategory/{productId}/{categoryId}")
    public String assignProductToCategory(@PathVariable int productId, @PathVariable int categoryId) {
        return productService.assignProductToCategory(productId, categoryId);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return  productService.deleteProduct(id);
    }

}
