package com.example.javazon.service;

import com.example.javazon.entities.Category;
import com.example.javazon.entities.Product;
import com.example.javazon.entities.dtos.ProductDto;
import com.example.javazon.model.PagedResponse;
import com.example.javazon.repository.CategoryRepository;
import com.example.javazon.repository.ProductRepository;
import com.example.javazon.service.mappers.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDto addProduct(ProductDto dto) {
        log.info("Adding Product {}" ,dto.getProductName());

        if (productRepository.existsByProductNameNative(dto.getProductName()) == 1){
            throw new RuntimeException("Product Name Already Exists");
        }

        Product product = productMapper.toEntity(dto);
        if (dto.getCategoryId()>0){
            Category category  = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + dto.getCategoryId()));

            product.setCategory(category);
        }

        product.setActive(true);
        return productMapper.toDto(productRepository.save(product));
    }

    public PagedResponse<ProductDto> getAllProduct(int page, int size) {

        int startRow = page * size;
        int endRow = startRow + size;

        List<Product> products = productRepository.getProductsPaged(startRow, endRow);

        //ask 7arby for this
        for (Product p : products) {
            if (p.getCategory() != null)
                Hibernate.initialize(p.getCategory());
            if (p.getProducer() != null)
                Hibernate.initialize(p.getProducer());
        }

        List<ProductDto> content = productMapper.toDto(products);

        int total = productRepository.getTotalItems();
        int totalPages = (int) Math.ceil((double) total / size);


        PagedResponse<ProductDto> response = new PagedResponse<>();
        response.setContent(content);
        response.setTotalElements(total);
        response.setTotalPages(totalPages);
        response.setPage(page);
        response.setSize(size);

        return response;
    }

    public ProductDto getProductById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return productMapper.toDto(product);
    }
    public ProductDto updateProduct(int id, ProductDto updatedProductDto) {

        Product currentProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        if (updatedProductDto.getProductName() != null) {
            currentProduct.setProductName(updatedProductDto.getProductName());
        }
        if (updatedProductDto.getProductDescription() != null) {
            currentProduct.setProductDescription(updatedProductDto.getProductDescription());
        }

        if (updatedProductDto.getProductPrice() != 0) {
            currentProduct.setProductPrice(updatedProductDto.getProductPrice());
        }

        if (updatedProductDto.getStockQuantity() != 0) {
            currentProduct.setStockQuantity(updatedProductDto.getStockQuantity());
        }

        if (updatedProductDto.getRating() != 0) {
            currentProduct.setRating(updatedProductDto.getRating());
        }

        if (updatedProductDto.getCategoryId() > 0) {
            Category category = categoryRepository.findById(updatedProductDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + updatedProductDto.getCategoryId()));
            currentProduct.setCategory(category);
        }

        currentProduct.setActive(true);

        Product savedProduct = productRepository.save(currentProduct);

        return productMapper.toDto(savedProduct);
    }


    public String assignProductToCategory(int productId, int categoryId ){
        Product product  = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        Category category  = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        product.setCategory(category);

        productRepository.save(product);

        return "Assigned successfully";
    }


    public String deleteProduct(int id)
    {
        if(!productRepository.existsById(id))
        {
            throw new RuntimeException("Product not found with id: "  + id );
        }
        productRepository.deleteById(id);
        return "Product deleted successfully!";
    }
}
