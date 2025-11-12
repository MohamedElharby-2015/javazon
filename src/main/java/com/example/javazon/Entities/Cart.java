package com.example.javazon.Entities;

import java.time.LocalDate;

public class Cart {

   private  int  cartId;
   private int userId;
   private LocalDate created_Date;


    public Cart(int cartId, int userId, LocalDate created_Date) {
        this.cartId = cartId;
        this.userId = userId;
        this.created_Date = created_Date;
    }

    public int getCartId() {
        return cartId;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDate getCreated_Date() {
        return created_Date;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCreated_Date(LocalDate created_Date) {
        this.created_Date = created_Date;
    }
}
