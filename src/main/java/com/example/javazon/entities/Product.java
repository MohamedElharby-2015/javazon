package com.example.javazon.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
public class Product extends SharedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
    private int productId;
    @Column(name = "PRODUCT_NAME", nullable = false, unique = true)
    private String productName;
    private String productDescription;
    private double productPrice;
    private int stockQuantity;
    private double rating;
    private boolean active;
    private String mainImgPath;


    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "PRODUCER_ID")
    private Producer producer;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Favourites> favourites = new ArrayList<>();

    public Product() {
    }


    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
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

    public Category getCategory() {
        return category;
    }

    public Producer getProducer() {
        return producer;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public List<Favourites> getFavourites() {
        return favourites;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void setFavourites(List<Favourites> favourites) {
        this.favourites = favourites;
    }

    public String getMainImgPath() {
        return mainImgPath;
    }

    public void setMainImgPath(String mainImgPath) {
        this.mainImgPath = mainImgPath;
    }
}
