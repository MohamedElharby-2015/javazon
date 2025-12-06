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

    default void updateEntityFromDto(ProductDto dto, Product entity) {
        if (dto.getProductName() != null) entity.setProductName(dto.getProductName());
        if (dto.getProductDescription() != null) entity.setProductDescription(dto.getProductDescription());
        if (dto.getProductPrice() != 0) entity.setProductPrice(dto.getProductPrice());
        if (dto.getStockQuantity() != 0) entity.setStockQuantity(dto.getStockQuantity());
        if (dto.getRating() != 0) entity.setRating(dto.getRating());
        if (dto.getMainImgPath() != null) entity.setMainImgPath(dto.getMainImgPath());
    }
}
