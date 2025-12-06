package com.example.javazon.controller;

import com.example.javazon.entities.Order;
import com.example.javazon.entities.dtos.OrderDto;
import com.example.javazon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto){
        return ResponseEntity.ok(orderService.addOrder(orderDto));
    }

    @GetMapping("/allUserOrders/{userId}")
    public ResponseEntity<List<OrderDto>> getAllUserOrders(@PathVariable int userId){
        return ResponseEntity.ok(orderService.getAllUserOrders(userId));
    }


}