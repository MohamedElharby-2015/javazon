package com.example.javazon.service;

import com.example.javazon.entities.CartItem;
import com.example.javazon.entities.Order;
import com.example.javazon.entities.OrderItem;
import com.example.javazon.entities.User;
import com.example.javazon.entities.dtos.OrderDto;
import com.example.javazon.enums.OrderStatus;
import com.example.javazon.repository.CartItemRepository;
import com.example.javazon.repository.OrderRepository;
import com.example.javazon.repository.UserRepository;
import com.example.javazon.service.mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private OrderMapper orderMapper;


    public OrderDto addOrder(OrderDto orderDto){
         User user = userRepository.findById(orderDto.getUserId()).orElseThrow(
                 () -> new RuntimeException("User NOt Found"));

        List<CartItem> cartItems = cartItemRepository.findByUserUserId(orderDto.getUserId());
        if(cartItems.isEmpty()){
            throw new RuntimeException("cart is empty");
        }

        double totalPrice = 0;

        Order order = new Order();
        order.setUser(user);
        order.setAddress(orderDto.getAddress());
        order.setStatus(OrderStatus.PENDING);
        order.setPaymentMethod(orderDto.getPaymentMethod());

        List<OrderItem> orderItems = new ArrayList<>();
        for ( CartItem cartItem :  cartItems){
            OrderItem orderItem = new OrderItem();

            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setSubtotal(cartItem.getQuantity()* cartItem.getProduct().getProductPrice());
            orderItem.setPriceAtPurchase(cartItem.getProduct().getProductPrice());
            orderItem.setOrder(order);

            totalPrice += orderItem.getSubtotal();
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);

        order.setTotalPrice(totalPrice);

        orderRepository.save(order);
        cartItemRepository.deleteAll(cartItems);
        return orderMapper.toDto(order);
    }

    public List<OrderDto> getAllUserOrders(int userId){
        return orderMapper.toDto(orderRepository.findByUserUserId(userId));
    }
}
