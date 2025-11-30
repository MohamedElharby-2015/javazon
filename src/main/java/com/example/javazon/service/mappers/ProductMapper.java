package com.example.javazon.service.mappers;

import com.example.javazon.entities.Product;
import com.example.javazon.entities.dtos.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // Entity -> DTO
    @Mapping(source = "category.categoryId", target = "categoryId")
    @Mapping(source = "category.categoryName", target = "categoryName")
    @Mapping(source = "category.categoryDescription", target="categoryDescription")
    @Mapping(source = "producer.producerId", target = "producerId")
    @Mapping(source = "producer.producerName", target = "producerName")
    ProductDto toDto(Product product);

    List<ProductDto> toDto(List<Product> products);

    // DTO -> Entity
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "producer", ignore = true)
    Product toEntity(ProductDto productDto);
}
