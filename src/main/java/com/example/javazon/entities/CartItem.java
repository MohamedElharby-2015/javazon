package com.example.javazon.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CART_ITEM")
public class CartItem extends SharedEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="cart_item_seq")
    @SequenceGenerator(name = "cart_item_seq", sequenceName = "CART_ITEM_SEQ",allocationSize = 1)
    private int cartItemId;

    private int quantity;
    private int subtotal;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;


    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public CartItem() {}

    public CartItem(int cartItemId, int quantity, int subtotal, Cart cart, Product product) {
        this.cartItemId = cartItemId;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.cart = cart;
        this.product = product;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public Cart getCart() {
        return cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
