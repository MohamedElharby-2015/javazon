package com.example.javazon.repository;

import com.example.javazon.entities.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites,Integer> {
    List<Favourites> findByUser_UserId(int userId);

}
