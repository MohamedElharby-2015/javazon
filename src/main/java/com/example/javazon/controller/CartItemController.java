package com.example.javazon.controller;

import com.example.javazon.entities.CartItem;
import com.example.javazon.entities.dtos.CartItemDto;
import com.example.javazon.model.BaseResponse;
import com.example.javazon.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/javazon/cartItem")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/addProductToCart")
    public CartItemDto addProductToCart(@RequestBody CartItemDto cartItemDto) {
        return cartItemService.addProductToCart(cartItemDto);
    }

    @GetMapping("/getUserCartItems/{userId}")
    public ResponseEntity<BaseResponse<CartItemDto>> getUserCartItems(@PathVariable int userId) {
        return  ResponseEntity.ok(cartItemService.getUserCartItems(userId));
    }

    @DeleteMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable int id) {
        cartItemService.deleteItem(id);
        return "Cart item deleted successfully!";
    }
    @PutMapping("/updateCount/{cartItemId}/user/{userId}")
    public ResponseEntity<BaseResponse<CartItemDto>> updateProductCount(
            @PathVariable int cartItemId,
            @PathVariable int userId,
            @RequestBody Map<String, Integer> requestBody) {

        int newCount = requestBody.get("count");
        BaseResponse<CartItemDto> response = cartItemService.updateProductCount(cartItemId, newCount, userId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/clearAllCart/{userId}")
    public String clearCart(@PathVariable int userId) {
        cartItemService.clearCart(userId);
        return "Cart cleared successfully!";
    }
}
