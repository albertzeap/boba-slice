package com.cognixia.jump.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Order;
import com.cognixia.jump.repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    OrderRepository orderRepository;


    public List<Order> getOrders(){
        
        List<Order> orders = orderRepository.findAll();

        return orders;
    }

    public Order getOrderById(int id){

        
    }


    public Boolean removeItem(String name){

        


        return null;
    }
}
