package com.example.javazon.service;

import com.example.javazon.entities.Favourites;
import com.example.javazon.entities.Product;
import com.example.javazon.entities.User;
import com.example.javazon.repository.FavouritesRepository;
import com.example.javazon.repository.ProductRepository;
import com.example.javazon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouritesService {
    @Autowired
    private FavouritesRepository favouritesRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Favourites addToFavourites(int userId , int productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Favourites favourites = new Favourites(user,product);
        return favouritesRepository.save(favourites);

    }

    public List<Favourites> getFavouritesByUser(int userId) {
        return favouritesRepository.findByUser_UserId(userId);
    }


}
