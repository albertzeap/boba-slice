package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cognixia.jump.model.OrderMenuItem;


// This repository is in charge of interaction with the OrderMenuItem table 
@Repository
public interface OrderMenuItemRepository extends JpaRepository<OrderMenuItem, Integer> {
    
    // @Transactional
	// @Modifying
	// @Query(value ="delete from order_menu_item o where o.menu_item_id = :menuItemId and o.order_id = :orderId", nativeQuery = true)
	// public List<OrderMenuItem> deleteItemById(@Param(value="menuItemId") int menuItemId, @Param(value="orderId") int orderId);
	
	@Transactional
	@Modifying
	@Query(value ="select * from order_menu_item o where o.menu_item_id = :menuItemId and o.order_id = :orderId", nativeQuery = true)
	public List<OrderMenuItem> existsItemById(@Param(value="menuItemId") int menuItemId, @Param(value="orderId") int orderId);
}
