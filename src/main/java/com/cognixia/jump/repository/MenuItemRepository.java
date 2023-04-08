package com.cognixia.jump.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    
    public Optional<MenuItem> findByName(String name);

    // Finds the menu based on type of menu item
    public List<MenuItem> findByType(String type);

    


}
