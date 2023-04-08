package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.MenuItem;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.model.OrderMenuItem;
import com.cognixia.jump.repository.MenuItemRepository;
import com.cognixia.jump.repository.OrderMenuItemRepository;
import com.cognixia.jump.repository.OrderRepository;

import com.cognixia.jump.model.Order;
import com.cognixia.jump.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepo;

    // Repository for the relationship table
    @Autowired 
    OrderMenuItemRepository orderMenuRepo;

    // Repository for menu items
    @Autowired
    MenuItemRepository menuItemRepo;

    public Order createOrder(@Valid Order order) throws Exception {
		
		Optional<Order> exists = orderRepo.findById(order.getId());
		
		if(exists.isPresent()) {
			throw new Exception("Order with id: " + order.getId() + " already exists");
		}
		
		return orderRepo.save(order);
	}
    
    // Gets the history of user orders
    public List<Order> getOrders(){
        
        List<Order> orders = orderRepo.findAll();

        return orders;
    }

    public Order getOrderById(int id) throws ResourceNotFoundException{

        Optional<Order> exists = orderRepo.findById(id);

        if(!exists.isPresent()){
            throw new ResourceNotFoundException("Order id", id);
        }
        return exists.get();
    }




    public OrderMenuItem addItem(int menuItemId, int orderId) throws ResourceNotFoundException{

        // Find menu item that is being added
        Optional<MenuItem> menuItem = menuItemRepo.findById(menuItemId);
        
        // Check if menu item exists
        if(!menuItem.isPresent()){
            throw new ResourceNotFoundException("Menu Item", menuItemId);
        }

        // Add the item to the already existing order
        Order order = getOrderById(orderId);

        // Create a OrderMenu object
        OrderMenuItem newOrderItem = new OrderMenuItem(null, order, menuItem.get());

        // Save it to the order_menu_item table
        orderMenuRepo.save(newOrderItem);

        return newOrderItem;
    }


    public Boolean deleteItem(int menuItemId, int orderId) throws ResourceNotFoundException {
    	List<OrderMenuItem> exists = orderMenuRepo.existsItemById(menuItemId, orderId);
    	
    	if(!exists.isEmpty()) { 
            orderMenuRepo.deleteById(exists.get(0).getId());
    		return true;
    	}
    	
        throw new ResourceNotFoundException("Menu item", menuItemId);
    }
}
