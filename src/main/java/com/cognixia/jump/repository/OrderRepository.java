package com.cognixia.jump.repository;

import javax.transaction.Transactional;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value="select * from order_menu_item o join menu_item m on o.menu_item_id = m.id join user_order u on o.order_id = u.order_id where user_id = ?1 ", nativeQuery = true)
    public List<MenuItem> viewOrder();
       
    @Query(value = "INSERT into order_menu_item (id, menu_item_id, order_id) values (:id, :menuItemId, :orderId) ", nativeQuery = true)
    public int addToOrder(@Param(value="id") int id, @Param(value="menuItemId") int menuItemId, @Param(value="orderId") int orderId);
}