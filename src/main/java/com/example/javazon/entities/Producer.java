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
}
