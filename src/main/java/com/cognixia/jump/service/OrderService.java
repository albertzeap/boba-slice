package com.cognixia.jump.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.MenuItem;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.model.OrderMenuItem;
import com.cognixia.jump.model.User;
import com.cognixia.jump.model.UserOrder;
import com.cognixia.jump.repository.MenuItemRepository;
import com.cognixia.jump.repository.OrderMenuItemRepository;
import com.cognixia.jump.repository.OrderRepository;
import com.cognixia.jump.repository.UserOrderRepository;
import com.cognixia.jump.repository.UserRepository;

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

    // Repository for User order relationships
    // Will be used when a user creates a new order
    @Autowired
    UserOrderRepository userOrderRepo;

    // Will be used to identify which user is active
    @Autowired
    UserRepository userRepo;

    public Order createOrder(int userId) throws Exception {

        // Check if the user is valid 
        Optional<User> valid = userRepo.findById(userId);
		
        // Must be a user in order to place an order
		if(!valid.isPresent()) {
			throw new Exception("Not a valid user");
		}

        // Creates the new order and saves it to the database
        Date currentDate = new Date();
        Order newOrder = new Order(null, 0.00, currentDate, false );
        Order created = orderRepo.save(newOrder);

        // Link the order with the user and save it to the database
        UserOrder newUserOrder = new UserOrder(null,valid.get(), newOrder);
		userOrderRepo.save(newUserOrder);

		return created;
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

        // Update the total price of the order
        Double totalPrice = menuItem.get().getPrice() + order.getTotalPrice();
        orderRepo.updateTotalPrice(totalPrice, orderId);

        // Create an OrderMenu object
        OrderMenuItem newOrderItem = new OrderMenuItem(null, order, menuItem.get());

        // Save it to the order_menu_item table
        orderMenuRepo.save(newOrderItem);

        return newOrderItem;
    }


    public Boolean deleteItem(int menuItemId, int orderId) throws ResourceNotFoundException {

    	List<OrderMenuItem> exists = orderMenuRepo.existsItemById(menuItemId, orderId);

        // Find the corresponding menu item
        Optional<MenuItem> menuItem = menuItemRepo.findById(menuItemId);

        // Find the corresponding order item 
        Optional<Order> order = orderRepo.findById(orderId);
    	
        // Check if the item exists in the order
        // If this check passes it means a menu item and order exists
        // We could error check for the other items if we wanted to be thorough
    	if(!exists.isEmpty()) { 

            // Just deletes one in case they ordered multiple and want to keep the other orders
            orderMenuRepo.deleteById(exists.get(0).getId());

            // Obtain the new total price of the order and update
            Double totalPrice = order.get().getTotalPrice() - menuItem.get().getPrice();
            orderRepo.updateTotalPrice(totalPrice, orderId);

    		return true;
    	}
    	
        throw new ResourceNotFoundException("Menu item", menuItemId);
    }
}
