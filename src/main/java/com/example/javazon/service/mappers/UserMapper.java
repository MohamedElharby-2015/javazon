package com.example.javazon.service.mappers;

import com.example.javazon.entities.User;
import com.example.javazon.entities.dtos.UserDto;
import com.example.javazon.entities.dtos.UserRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    List<UserDto> toDto(List<User> users);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "carts", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntity(UserRegisterDto dto);
}
