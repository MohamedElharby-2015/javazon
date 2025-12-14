package com.example.javazon.service.mappers;

import com.example.javazon.entities.CartItem;
import com.example.javazon.entities.dtos.CartItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(source = "product.productId", target = "productId")
    @Mapping(source = "product.productName", target = "productName")
    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "user.email", target = "userEmail")
    CartItemDto toDto(CartItem cartItem);

    List<CartItemDto> toDto(List<CartItem> cartItems);

    CartItem toEntity(CartItemDto cartItemDto);

}
