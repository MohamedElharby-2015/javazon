package com.example.javazon.service;

import com.example.javazon.entities.Cart;
import com.example.javazon.entities.User;
import com.example.javazon.repository.CartRepository;
import com.example.javazon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;


    public Cart addCart(int userId, Cart cart) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        cart.setUser(user);
        return cartRepository.save(cart);

    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(int id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart Not Found"));
    }


    public void deleteCart(int id) {
       cartRepository.deleteById(id);
    }
}
