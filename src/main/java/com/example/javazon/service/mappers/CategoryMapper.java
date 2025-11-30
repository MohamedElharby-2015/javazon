package com.example.javazon.service.mappers;

import com.example.javazon.entities.Category;
import com.example.javazon.entities.dtos.CategoryDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDto categoryDto);

    List<CategoryDto> toDto(List<Category> categories);

    CategoryDto toDto(Category categorie);
}
