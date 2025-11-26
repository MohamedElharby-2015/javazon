package com.example.javazon.controller;

import com.example.javazon.entities.Cart;
import com.example.javazon.repository.CartRepository;
import com.example.javazon.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addCart/{id}")
    public Cart addCart(@RequestBody Cart cart,@PathVariable int id) {
        return cartService.addCart(id,cart);

    }

    @GetMapping("/getAllCarts")
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("getCartById/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable int id) {
        Cart cart = cartService.getCartById(id);
        return ResponseEntity.ok(cart);
    }


    @DeleteMapping("/deleteCart/{id}")
    public String deleteCart(@PathVariable int id) {
        cartService.deleteCart(id);
        return "Cart deleted successfully!";
    }
}
