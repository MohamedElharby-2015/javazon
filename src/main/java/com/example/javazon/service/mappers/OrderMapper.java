package com.example.javazon.service.mappers;

import com.example.javazon.entities.CartItem;
import com.example.javazon.entities.Order;
import com.example.javazon.entities.dtos.CartItemDto;
import com.example.javazon.entities.dtos.OrderDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderDto orderDto);

    OrderDto toDto(Order order);

   List<OrderDto>  toDto(List<Order> order);
}
