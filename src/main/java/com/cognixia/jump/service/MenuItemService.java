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
	
	public List<MenuItem> getMenuItemes() throws Exception {
		List<MenuItem> MenuItemList = menuItemRepo.findAll();
		
		// check if there is a list of drinks available
		if(MenuItemList.isEmpty()) {
			throw new Exception("No MenuItemes available!");
		}
		
		return MenuItemList;
	}
	
	public MenuItem getMenuItemById(int id) throws Exception {
		Optional<MenuItem> found = menuItemRepo.findById(id);
		
		if(!found.isPresent()) {
			throw new Exception("MenuItem with id " + id + " not found");
		}
		
		return found.get();
	}
	
	public MenuItem createMenuItem(MenuItem MenuItem) throws Exception {
		// we will add proper error checks later on
		Optional<MenuItem> exists = menuItemRepo.findByName(MenuItem.getName());
		
		if(exists.isPresent()) {
			throw new Exception("MenuItem: " + MenuItem.getName() + " already exists");
		}
		
		return menuItemRepo.save(MenuItem);
	}

}
