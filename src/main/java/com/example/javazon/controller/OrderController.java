package com.example.javazon.controller;

import com.example.javazon.entities.Order;
import com.example.javazon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder/{userId}")
    public Order addOrder(@PathVariable int userId, @RequestBody Order order) {
        return orderService.addOrder(userId, order);
    }

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrder();
    }

    @GetMapping("/getOrderById/{id}")
    public Order getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @PutMapping("/update/{id}")
    public Order updateOrder(@PathVariable int id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return "Order deleted successfully!";
    }
}
