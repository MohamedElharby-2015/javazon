package com.example.javazon.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCER")
public class Producer extends SharedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int producerId;

    private String producerName;
    private String producerDescription;
    private String producerCountry;
    private String producerWebsite;
    private double rating;

    @OneToMany(mappedBy = "producer", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Producer() {}


    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public String getProducerDescription() {
        return producerDescription;
    }

    public void setProducerDescription(String producerDescription) {
        this.producerDescription = producerDescription;
    }

    public String getProducerCountry() {
        return producerCountry;
    }

    public void setProducerCountry(String producerCountry) {
        this.producerCountry = producerCountry;
    }

    public String getProducerWebsite() {
        return producerWebsite;
    }

    public void setProducerWebsite(String producerWebsite) {
        this.producerWebsite = producerWebsite;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }
}
