package com.example.javazon.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "RELATED_PRODUCT")
public class RelatedProduct extends SharedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int relatedId;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Product parent;

    @ManyToOne
    @JoinColumn(name = "CHILD_ID")
    private Product child;

    private String relationType;
    private int sortOrder;

    public RelatedProduct() {}
}
