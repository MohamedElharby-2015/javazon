package com.example.javazon.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CART_ITEM")
public class CartItem {

    @Id
    private int cartId;
    private int productPd;
    private int quantity;


    public CartItem(int cartId, int productPd, int quantity) {
        this.cartId = cartId;
        this.productPd = productPd;
        this.quantity = quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public int getProductPd() {
        return productPd;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setProductPd(int productPd) {
        this.productPd = productPd;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

