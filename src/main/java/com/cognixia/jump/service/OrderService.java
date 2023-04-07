package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.MenuItem;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.model.OrderMenuItem;
import com.cognixia.jump.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepo;

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

    // Gets a specific order
    public List<MenuItem> getOrderById(int id){

        return null;
    }

    public MenuItem addMenuItem(MenuItem menuItem){

        OrderMenuItem orderMenuItem = new(null, )
        orderRepo.addToOrder(0, menuItem.getId(), 0);

        return null;

    }

    


    public Boolean removeItem(String name){

        


        return null;
    }
}
