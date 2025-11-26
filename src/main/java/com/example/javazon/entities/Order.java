package com.example.javazon.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ORDERS")
public class Order extends SharedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "ORDER_SEQ",allocationSize = 1)
    private int orderId;

    private int totalAmount;
    private LocalDate orderDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "USER_ID" , nullable = false)
    @JsonBackReference
    private User user;

    public Order() {}

    public Order(int orderId, int totalAmount, LocalDate orderDate, String status, User user) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.status = status;
        this.user = user;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
