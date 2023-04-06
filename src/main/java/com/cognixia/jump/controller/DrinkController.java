package com.cognixia.jump.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Drink;
import com.cognixia.jump.service.DrinkService;

@RestController
@RequestMapping("/api")
public class DrinkController {
    @Autowired
    DrinkService drinkService;

    @GetMapping("/drinks")
    public ResponseEntity<?> getDrinks() throws Exception{

        List<Drink> drinks = drinkService.getDrinks();

        return ResponseEntity.status(200).body(drinks);
    }

    // getting a specific drink
    @GetMapping("/drink/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) throws Exception {

        Drink found = drinkService.getDrinkById(id);

        return ResponseEntity.status(200).body(found);
    }

    // creating a drink
    @PostMapping("/drink")
    public ResponseEntity<?> createUser(@RequestBody Drink drink) throws Exception{
        
        drink.setId(null);

        Drink created = drinkService.createDrink(drink);
        return ResponseEntity.status(201).body(created);
    }
    
}
