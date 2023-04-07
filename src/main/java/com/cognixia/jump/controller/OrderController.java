package com.cognixia.jump.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Order;
import com.cognixia.jump.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
	OrderService orderService;
	
	 // creating a order
     @PostMapping("/order")
     public ResponseEntity<?> createDrink(@Valid @RequestBody Order order) throws Exception{
         
         order.setId(null);
 
         Order created = orderService.createOrder(order);
         return ResponseEntity.status(201).body(created);
     }

    
    
}
