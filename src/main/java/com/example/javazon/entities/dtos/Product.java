package com.example.javazon.entities.dtos;

import jakarta.persistence.Column;

public class Product {
    @Column(name = "PRODUCT_NAME")
    private String productName;
    private double productPrice;
    private int stockQuantity;
    private double rating;
    private boolean active;
    public Product(){}


    public Product(String productName, double productPrice, int stockQuantity, double rating, boolean active) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.stockQuantity = stockQuantity;
        this.rating = rating;
        this.active = active;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public double getRating() {
        return rating;
    }

    public boolean isActive() {
        return active;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
