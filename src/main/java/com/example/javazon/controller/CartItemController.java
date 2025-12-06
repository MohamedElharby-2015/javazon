package com.example.javazon.controller;

import com.example.javazon.entities.CartItem;
import com.example.javazon.entities.dtos.CartItemDto;
import com.example.javazon.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/addProductToCart")
    public String addProductToCart(@RequestBody CartItemDto cartItemDto) {
        return cartItemService.addProductToCart(cartItemDto);
    }

    @GetMapping("/getUserCartItems/{userId}")
    public List<CartItemDto> getUserCartItems(@PathVariable int userId) {
        return cartItemService.getUserCartItems(userId);
    }

    @DeleteMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable int id) {
        cartItemService.deleteItem(id);
        return "Cart item deleted successfully!";
    }
}
