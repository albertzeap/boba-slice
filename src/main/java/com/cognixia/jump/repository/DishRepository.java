package com.cognixia.jump.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer>{
	
	public Optional<Dish> findByName(String name);

}
