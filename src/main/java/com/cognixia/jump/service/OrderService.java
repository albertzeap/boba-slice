package com.cognixia.jump.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Order;
import com.cognixia.jump.repository.OrderRepository;

import com.cognixia.jump.model.Order;
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
    
    @Autowired
    OrderRepository orderRepository;


    public List<Order> getOrders(){
        
        List<Order> orders = orderRepository.findAll();

        return orders;
    }

    public Order getOrderById(int id){

        return null;
    }


    public Boolean removeItem(String name){

        


        return null;
    }
}
