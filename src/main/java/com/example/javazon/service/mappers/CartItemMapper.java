package com.example.javazon.service.mappers;

import com.example.javazon.entities.CartItem;
import com.example.javazon.entities.dtos.CartItemDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItem toEntity(CartItemDto cartItemDto);

    CartItemDto toDto(CartItem cartItemDto);

   List<CartItemDto>  toDto(List<CartItem> cartItemDto);
}
