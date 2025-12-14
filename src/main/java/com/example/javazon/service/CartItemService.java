package com.example.javazon.service;

import com.example.javazon.entities.CartItem;
import com.example.javazon.entities.Product;
import com.example.javazon.entities.User;
import com.example.javazon.entities.dtos.CartItemDto;
import com.example.javazon.exceptions.EntityNotFoundException;
import com.example.javazon.model.BaseResponse;
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


    public CartItemDto addProductToCart(CartItemDto cartItemDto) {
        User user = userRepository.findByEmail(cartItemDto.getUserEmail());

        Product product = productRepository.findById(cartItemDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem currentCartItem = cartItemRepository.findByProductProductId(cartItemDto.getProductId());
        if (currentCartItem != null) {
            updateProductCount(currentCartItem.getCartItemId(), cartItemDto.getQuantity() + 1, cartItemDto.getUserId());
            return cartItemMapper.toDto(currentCartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemDto.getQuantity());
            cartItem.setSubtotal(product.getProductPrice() * cartItem.getQuantity());
            cartItem.setMainImgPath(product.getMainImgPath());
            cartItemRepository.save(cartItem);
            return cartItemMapper.toDto(cartItem);
        }

    }

    public BaseResponse<CartItemDto> getUserCartItems(int userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

        List<CartItem> cartItems = cartItemRepository.findByUserUserId(userId);
        List<CartItemDto> cartItemsDto = cartItemMapper.toDto(cartItems);

        double totalAmount = cartItems.stream()
                .mapToDouble(item -> item.getQuantity() * item.getProduct().getProductPrice())
                .sum();

        BaseResponse<CartItemDto> baseResponse = new BaseResponse<>();
        baseResponse.setContent(cartItemsDto);
        baseResponse.setTotalElements(cartItemsDto.size());
        baseResponse.setTotalAmount(totalAmount);

        return baseResponse;
    }
    public BaseResponse<CartItemDto> updateProductCount(int cartItemId, int count, int userId) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        item.setQuantity(count);
        item.setSubtotal(item.getProduct().getProductPrice() * count);
        cartItemRepository.save(item);

        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }

        List<CartItemDto> cartItemsDto = cartItemMapper.toDto(
                cartItemRepository.findByUserUserId(userId)
        );

        BaseResponse<CartItemDto> baseResponse = new BaseResponse<>();
        baseResponse.setContent(cartItemsDto);
        baseResponse.setTotalElements(cartItemsDto.size());

        return baseResponse;
    }


    public void deleteItem(int id) {
        cartItemRepository.deleteById(id);
    }

    public void clearCart(int userId) {
        List<CartItem> items = cartItemRepository.findByUserUserId(userId);
        cartItemRepository.deleteAll(items);
    }
}
