package com.example.javazon.entities.shared;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public class SharedEntity {
    @CreatedDate
    @Column(name = "created_on", updatable = false)
    private LocalDateTime createdOn;

    @CreatedBy
    @Column(name = "created_by", length = 100, updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @LastModifiedBy
    @Column(name = "updated_by", length = 100)
    private String updatedBy;

}
