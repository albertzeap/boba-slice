package com.cognixia.jump.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.MenuItem;
import com.cognixia.jump.service.MenuItemService;

@RestController
@RequestMapping("/api")
public class MenuItemController {

    @Autowired
	MenuItemService menuItemService;
	
    // Get the pizza menu
	@GetMapping("/menu/dishes")
	public ResponseEntity<?> getDishMenu() throws Exception {
		List<MenuItem> dishes = menuItemService.getDishes(); 
		
		return ResponseEntity.status(200).body(dishes);
	}

    // Get the boba menu
    @GetMapping("/menu/drinks")
    public ResponseEntity<?> getDrinkMenu () throws Exception {
        List<MenuItem> drinks = menuItemService.getDrinks();

        return ResponseEntity.status(200).body(drinks);
    }    

    

    // creating a menuItem
    @PostMapping("/menuItem")
    public ResponseEntity<?> createMenuItem(@Valid @RequestBody MenuItem menuItem) throws Exception{
        
        menuItem.setId(null);

        MenuItem created = menuItemService.createMenuItem(menuItem);
        return ResponseEntity.status(201).body(created);
    }

}
