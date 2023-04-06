package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Drink;
import com.cognixia.jump.repository.DrinkRepository;


@Service
public class DrinkService {
    @Autowired
    DrinkRepository drinkRepo;

    public List<Drink> getDrinks() throws Exception{

        List<Drink> isEmpty = drinkRepo.findAll();

        // Check if there is a list of drinks available
        if(isEmpty.isEmpty()){
            // We can create custom exceptions and a global exception handler later
            throw new Exception("No current users");
        }

        return drinkRepo.findAll();
    }

    public Drink getDrinkById(int id) throws Exception{

        Optional<Drink> found = drinkRepo.findById(id);

        if(!found.isPresent()){
            throw new Exception("Drink with id " + id + " not found");
        }

        return found.get();
    }

    public Drink createDrink(Drink drink) throws Exception{  
        
        
        // we will add proper error checks later on 
        // for example we need to check if this drink already exists in the 
        // database. For now we will just save
        Optional<Drink> exists = drinkRepo.findByName(drink.getName());

        if(exists.isPresent()){
            throw new Exception("Drink: " + drink.getName() + " already exists");
        }


        return drinkRepo.save(drink);
    }
}
