package com.example.javazon.controller;

import com.example.javazon.entities.Favourites;
import com.example.javazon.repository.FavouritesRepository;
import com.example.javazon.service.FavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favourites")
public class    FavouritesController {

    @Autowired
    private FavouritesRepository favouritesRepository;

    @Autowired
    private FavouritesService favouritesService;

    @PostMapping("/addToFavourites")
    public Favourites addToFavourites(@RequestParam int userId, @RequestParam int productId) {
        return favouritesService.addToFavourites(userId, productId);
    }
}
