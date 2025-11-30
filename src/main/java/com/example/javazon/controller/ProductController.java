package com.example.javazon.controller;
import com.example.javazon.entities.Product;
import com.example.javazon.entities.dtos.ProductDto;
import com.example.javazon.model.PagedResponse;
import com.example.javazon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<PagedResponse<ProductDto>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(productService.getAllProduct(page, size));
    }

    @GetMapping("/getProductById/{id}")
    public ProductDto getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PutMapping("/updateProduct/{id}")
    public ProductDto updateProduct(@PathVariable int id, @RequestBody ProductDto product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id) {
        return  productService.deleteProduct(id);
    }
    @PutMapping("/assignProductToCategory/{productId}/{categoryId}")
    public String assignProductToCategory (@PathVariable  int productId , @PathVariable int categoryId){
       return productService.assignProductToCategory(productId, categoryId);
    }


}
