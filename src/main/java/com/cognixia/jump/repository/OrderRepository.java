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
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value="select * from order_menu_item where id = ?1", nativeQuery = true)
    public OrderMenuItem viewOrder(int orderId);
       
    @Query(value = "INSERT into order_menu_item (id, menu_item_id, order_id) values (:id, :menuItemId, :orderId) ", nativeQuery = true)
    public int addToOrder(@Param(value="id") int id, @Param(value="menuItemId") int menuItemId, @Param(value="orderId") int orderId);
		
	// This does the actual deletion from the orderMenuItem table in MySQL
	@Transactional
	@Modifying
	@Query(value ="delete from order_menu_item o where o.menu_item_id = :menuItemId and o.order_id = :orderId", nativeQuery = true)
	public List<OrderMenuItem> deleteItemById(@Param(value="menuItemId") int menuItemId, @Param(value="orderId") int orderId);
	
	@Transactional
	@Modifying
	@Query(value ="select * from order_menu_item o where o.menu_item_id = :menuItemId and o.order_id = :orderId", nativeQuery = true)
	public List<OrderMenuItem> existsItemById(@Param(value="menuItemId") int menuItemId, @Param(value="orderId") int orderId);
    
}