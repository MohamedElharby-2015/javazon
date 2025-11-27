package com.example.javazon.repository;

import com.example.javazon.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    User findByEmail(String email);
}
