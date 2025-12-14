package com.example.javazon.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/*MappedSuperclass => this notify spring to
    not create table for this entity but each one extend from it
    put columns in your table
*/

@MappedSuperclass
public class SharedEntity {

    @Column(name = "created_on", updatable = false)
    private LocalDateTime createdOn;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    private LocalDateTime updatedOn;

    private String updatedBy;

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

// PrePersist => before insert each record into table make this method logic
//   work on insert
    @PrePersist
    public void onCreate (){

        this.createdOn = LocalDateTime.now();
        this.updatedOn = LocalDateTime.now();

        this.createdBy = "sara";
        this.updatedBy = "sara";
    }

    @PreUpdate
    public void onUpdate(){
        this.updatedOn = LocalDateTime.now();
        this.updatedBy = "mustafa";

    }



}
