package com.example.javazon.service;

import com.example.javazon.entities.CartItem;
import com.example.javazon.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItem addItem(CartItem cartItem) {
        CartItem existing = cartItemRepository.findById(cartItem.getCartItemId())
                .orElse(cartItem);
        existing.setQuantity(cartItem.getQuantity());
        return cartItemRepository.save(existing);
    }


    public List<CartItem> getAllItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getItemById(int id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    public void deleteItem(int id) {
        cartItemRepository.deleteById(id);
    }
}
