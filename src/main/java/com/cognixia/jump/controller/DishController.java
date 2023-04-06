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

import com.cognixia.jump.model.Dish;
import com.cognixia.jump.service.DishService;

@RestController
@RequestMapping("/api")
public class DishController {
	
	@Autowired
	DishService dishService;
	
	@GetMapping("/pizza/menu")
	public ResponseEntity<?> getPizzaMenu() throws Exception {
		List<Dish> dishes = dishService.getDishes(); // this will get all the food from dish table and will be displayed as a menu
		
		return ResponseEntity.status(200).body(dishes);
	}
	
    // getting a specific drink
    @GetMapping("/dish/{id}")
    public ResponseEntity<?> getDishById(@PathVariable int id) throws Exception {

        Dish found = dishService.getDishById(id);

        return ResponseEntity.status(200).body(found);
    }

    // creating a drink
    @PostMapping("/dish")
    public ResponseEntity<?> createDish(@RequestBody Dish dish) throws Exception{
        
        dish.setId(null);

        Dish created = dishService.createDish(dish);
        return ResponseEntity.status(201).body(created);
    }
	
}
