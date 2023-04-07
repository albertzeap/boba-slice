package com.cognixia.jump.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	@Transactional
	@Modifying
	@Query(value ="delete o from order_drink o where o.drink_id = :id", nativeQuery = true)
	public int deleteDrink(@Param(value="id") int id);
	
    
    @Transactional
	@Modifying
	@Query(value ="DELETE o FROM order_dish o WHERE o.dish_id=:id", nativeQuery = true)
	public int deleteDish(@Param(value="id") int id);
    
    @Transactional
	@Modifying
	@Query(value ="SELECT o FROM order_drink o WHERE o.drink_id = :id", nativeQuery = true)
	public int getDrinkById(@Param(value="id") int id);
    
}
