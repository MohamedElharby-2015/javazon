package com.example.javazon.service;

import com.example.javazon.entities.CartItem;
import com.example.javazon.entities.Product;
import com.example.javazon.entities.User;
import com.example.javazon.entities.dtos.CartItemDto;
import com.example.javazon.repository.CartItemRepository;
import com.example.javazon.repository.ProductRepository;
import com.example.javazon.repository.UserRepository;
import com.example.javazon.service.mappers.CartItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    CartItemMapper cartItemMapper;


    public String addProductToCart(CartItemDto cartItemDto) {
        User user = userRepository.findById(cartItemDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(cartItemDto.getProductName())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItem.getQuantity());
        cartItem.setSubtotal((int) (product.getProductPrice() * cartItem.getQuantity()));
        cartItemRepository.save(cartItem);
        return "Product Added To cart Item Successfully";
    }

    public List<CartItemDto> getUserCartItems(int userId) {
        return cartItemMapper.toDto(cartItemRepository.findByUserUserId(userId));
    }

    public void deleteItem(int id) {
        cartItemRepository.deleteById(id);
    }

    public void clearCart(int userId) {
        List<CartItem> items = cartItemRepository.findByUserUserId(userId);
        cartItemRepository.deleteAll(items);
    }
}
