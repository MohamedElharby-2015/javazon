package com.example.javazon.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CART")
public class Cart extends SharedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="cart_seq")
    @SequenceGenerator(name = "cart_seq", sequenceName = "CART_SEQ",allocationSize = 1)
    private int cartId;
    private int totalAmount;
    private int totalPrice;

    @OneToOne
    @JoinColumn(name = "USER_ID",nullable = false)
    private User user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart() {}

    public Cart(int cartId, int totalAmount, int totalPrice, User user) {
        this.cartId = cartId;
        this.totalAmount = totalAmount;
        this.totalPrice = totalPrice;
        this.user = user;
    }


    public int getCartId() {
        return cartId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public User getUser() {
        return user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
