package com.example.javazon.repository;

import com.example.javazon.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END " +
            "FROM CATEGORY c " +
            "WHERE c.CATEGORY_NAME = :name", nativeQuery = true)
    int existsByCategoryNameNative(@Param("name") String name);
}
