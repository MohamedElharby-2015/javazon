package com.example.javazon.service;

import com.example.javazon.entities.Order;
import com.example.javazon.entities.User;
import com.example.javazon.repository.OrderRepository;
import com.example.javazon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    public Order addOrder(int userId, Order order) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);
        return orderRepository.save(order);
    }


    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order updateOrder(int id, Order updatedOrder) {
        return orderRepository.findById(id).map(order -> {
            order.setTotalAmount(updatedOrder.getTotalAmount());
            order.setStatus(updatedOrder.getStatus());
            order.setOrderDate(updatedOrder.getOrderDate());
            return orderRepository.save(order);
        }).orElse(null);
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
