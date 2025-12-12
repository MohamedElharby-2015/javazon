package com.example.javazon.repository;

import com.example.javazon.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByUserUserId(int userId);
    CartItem findByProductProductId(int userId);
    boolean existsByProductProductId(int userId);
}