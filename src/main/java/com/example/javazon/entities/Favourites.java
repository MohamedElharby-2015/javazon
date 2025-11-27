package com.example.javazon.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "FAVOURITES")
public class Favourites extends SharedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int favouriteId;

    @ManyToOne
    @JoinColumn(name = "USER_ID",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID",nullable = false)
    private Product product;

    public Favourites() {}

    public Favourites( User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public int getFavouriteId() {
        return favouriteId;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public void setFavouriteId(int favouriteId) {
        this.favouriteId = favouriteId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
