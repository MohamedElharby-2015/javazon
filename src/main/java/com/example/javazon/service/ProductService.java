package com.example.javazon.service;

import com.example.javazon.entities.Product;
import com.example.javazon.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(int id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
//            product.setName(updatedProduct.getName());
//            product.setDescription(updatedProduct.getDescription());
//            product.setPrice(updatedProduct.getPrice());
//            product.setStockQuantity(updatedProduct.getStockQuantity());

            return productRepository.save(product);
        }).orElse(null);
    }

    public void deleteProduct(int id)
    {
        productRepository.deleteById(id);
    }
}
