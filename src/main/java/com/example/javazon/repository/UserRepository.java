package com.example.javazon.repository;

import com.example.javazon.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END " +
            "FROM USERS u " +
            "WHERE u.USER_NAME = :name", nativeQuery = true)
    int existsByUserNameNative(@Param("name") String name);
    boolean existsByEmail(String email);
    @Query(value = "SELECT * FROM USERS u WHERE u.EMAIL = :email" , nativeQuery = true)
    Optional<User> findByEmail(String email);
}
