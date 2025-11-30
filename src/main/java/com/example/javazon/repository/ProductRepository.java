package com.example.javazon.repository;

import com.example.javazon.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> , JpaSpecificationExecutor<Product> {

    @Query(
            value = "SELECT * FROM (" +
                    "SELECT p.*, ROWNUM rnum FROM PRODUCT p " +
                    "WHERE ROWNUM <= :endRow" +
                    ") WHERE rnum > :startRow",
            nativeQuery = true
    )
    List<Product> getProductsPaged(@Param("startRow") int startRow,
                                   @Param("endRow") int endRow);


    @Query(value = "SELECT COUNT(*) FROM PRODUCT", nativeQuery = true)
    int getTotalItems();


    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END " +
            "FROM PRODUCT p " +
            "WHERE p.PRODUCT_NAME = :name", nativeQuery = true)
    int existsByProductNameNative(@Param("name") String name);
}
