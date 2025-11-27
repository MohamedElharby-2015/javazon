package com.example.javazon.controller;

import com.example.javazon.entities.CartItem;
import com.example.javazon.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/addItem")
    public CartItem addItem(@RequestBody CartItem item)  {
        return cartItemService.addItem(item);
    }

    @GetMapping("/getAllItems")
    public List<CartItem> getAllItems() {
        return cartItemService.getAllItems();
    }

    @GetMapping("/getCartItemById/{id}")
    public CartItem getCartItemById(@PathVariable int id) {
        return cartItemService.getItemById(id);
    }

    @DeleteMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable int id) {
        cartItemService.deleteItem(id);
        return "Cart item deleted successfully!";
    }
}
