package com.example.javazon.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
public class Category extends SharedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "CATEGORY_SEQ", allocationSize = 1)
    private int categoryId;

    private String categoryName;
    private String categoryDescription;
    private boolean active;


    private String mainImgPath;


    public Category() {}

    public Category(String categoryName, String categoryDescription, boolean active) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.active = active;

    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public boolean isActive() {
        return active;
    }


    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getMainImgPath() {
        return mainImgPath;
    }

    public void setMainImgPath(String mainImgPath) {
        this.mainImgPath = mainImgPath;
    }
}
