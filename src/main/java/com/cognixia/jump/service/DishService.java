package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Dish;
import com.cognixia.jump.repository.DishRepository;

@Service
public class DishService {
	@Autowired
	DishRepository dishRepo;
	
	public List<Dish> getDishes() throws Exception {
		List<Dish> dishList = dishRepo.findAll();
		
		// check if there is a list of drinks available
		if(dishList.isEmpty()) {
			throw new Exception("No dishes available!");
		}
		
		return dishList;
	}
	
	public Dish getDishById(int id) throws Exception {
		Optional<Dish> found = dishRepo.findById(id);
		
		if(!found.isPresent()) {
			throw new Exception("Dish with id " + id + " not found");
		}
		
		return found.get();
	}
	
	public Dish createDish(Dish dish) throws Exception {
		// we will add proper error checks later on
		Optional<Dish> exists = dishRepo.findByName(dish.getName());
		
		if(exists.isPresent()) {
			throw new Exception("Dish: " + dish.getName() + " already exists");
		}
		
		return dishRepo.save(dish);
	}
	
	

}
