package com.cognixia.jump.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.UserOrder;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Integer> {

    
}
