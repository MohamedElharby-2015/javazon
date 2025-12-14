package com.example.javazon.entities;

import com.example.javazon.enums.OrderStatus;
import com.example.javazon.enums.PaymentMethod;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order extends SharedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "ORDER_SEQ", allocationSize = 1)
    private int orderId;

    private double totalPrice;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private String address;


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {}

    public int getOrderId() { return orderId; }
    public double getTotalPrice() { return totalPrice; }
    public OrderStatus getStatus() { return status; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public String getAddress() { return address; }
    public User getUser() { return user; }
    public List<OrderItem> getOrderItems() { return orderItems; }

    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setAddress(String address) { this.address = address; }
    public void setUser(User user) { this.user = user; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
}
