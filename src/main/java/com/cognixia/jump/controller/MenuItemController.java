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

import com.cognixia.jump.model.MenuItem;
import com.cognixia.jump.service.MenuItemService;

@RestController
@RequestMapping("/api")
public class MenuItemController {

    @Autowired
	MenuItemService menuItemService;
	
	@GetMapping("/menu")
	public ResponseEntity<?> getMenu() throws Exception {
		List<MenuItem> menuItem = menuItemService.getMenuItemes(); // this will get all the food from dish table and will be displayed as a menu
		
		return ResponseEntity.status(200).body(menuItem);
	}
	
    // getting a specific drink
    @GetMapping("/menuItem/{id}")
    public ResponseEntity<?> getMenuItemById(@PathVariable int id) throws Exception {

        MenuItem found = menuItemService.getMenuItemById(id);

        return ResponseEntity.status(200).body(found);
    }

    

    // creating a menuItem
    @PostMapping("/menuItem")
    public ResponseEntity<?> createMenuItem(@RequestBody MenuItem menuItem) throws Exception{
        
        menuItem.setId(null);

        MenuItem created = menuItemService.createMenuItem(menuItem);
        return ResponseEntity.status(201).body(created);
    }

}
