package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.MenuItem;
import com.cognixia.jump.repository.MenuItemRepository;

@Service
public class MenuItemService {

    @Autowired
	MenuItemRepository menuItemRepo;
	
	public List<MenuItem> getDishes () throws Exception {
		List<MenuItem> dishList = menuItemRepo.findByType("Dish");
		

		// check if there is a list of drinks available
		if(dishList.isEmpty()) {
			throw new Exception("No Menu Items available!");
		}
		
		return dishList;
	}

	public List<MenuItem> getDrinks() throws Exception {
		List<MenuItem> drinkList = menuItemRepo.findByType("Drink");

		if(drinkList.isEmpty()) {
			throw new Exception("No Menu Items available!");
		}
		return drinkList;
	}
	
	public MenuItem getMenuItemById(int id) throws Exception {
		Optional<MenuItem> found = menuItemRepo.findById(id);
		
		if(!found.isPresent()) {
			throw new Exception("MenuItem with id " + id + " not found");
		}
		
		return found.get();
	}
	
	public MenuItem createMenuItem(MenuItem menuItem) throws Exception {
		// we will add proper error checks later on
		Optional<MenuItem> exists = menuItemRepo.findByName(menuItem.getName());
		
		if(exists.isPresent()) {
			throw new Exception("MenuItem: " + menuItem.getName() + " already exists");
		}
		
		
		// menuItem.setId(null);
		return menuItemRepo.save(menuItem);
	}

}
