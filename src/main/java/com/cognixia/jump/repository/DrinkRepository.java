package com.cognixia.jump.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Drink;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Integer> {
    
    public Optional<Drink> findByName(String name);
}

