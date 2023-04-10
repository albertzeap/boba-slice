package com.cognixia.jump.repository;

import java.util.List;

import javax.transaction.Transactional;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Order;
import com.cognixia.jump.model.OrderMenuItem;

// This repository is only responsible for queries on the Order table 
// Queries ran on the relationship table are kept in a separate repository
// for easy understanding of this repo's functions

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	// This does the actual deletion from the orderMenuItem table in MySQL
	// @Transactional
	// @Modifying
	// @Query(value ="delete from order_menu_item o where o.menu_item_id = :menuItemId and o.order_id = :orderId", nativeQuery = true)
	// public List<OrderMenuItem> deleteItemById(@Param(value="menuItemId") int menuItemId, @Param(value="orderId") int orderId);
	
	// @Transactional
	// @Modifying
	// @Query(value ="select * from order_menu_item o where o.menu_item_id = :menuItemId and o.order_id = :orderId", nativeQuery = true)
	// public List<OrderMenuItem> existsItemById(@Param(value="menuItemId") int menuItemId, @Param(value="orderId") int orderId);

	// Updates the total price within the database
	@Transactional
	@Modifying
	@Query(value ="UPDATE orders SET orders.total_price = :totalPrice WHERE orders.id = :orderId", nativeQuery = true)
	public int updateTotalPrice(@Param(value="totalPrice") Double totalPrice, @Param(value="orderId") int orderId);
    
}