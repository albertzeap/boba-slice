package com.cognixia.jump.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Order;
import com.cognixia.jump.service.OrderService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    // Gets the history of orders
    @GetMapping("/orders")
    public ResponseEntity<?> getOrders(){

        // error check on frontend?
        List<Order> orders = orderService.getOrders();

        return ResponseEntity.status(200).body(orders);
    }

    // Gets a specific order
    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable int id) {
        

        

        return ResponseEntity.status(201).body(null);
    }
    




    @DeleteMapping("/order{name}")
    public ResponseEntity<?> removeItem(@PathVariable String name){

        orderService.removeItem(name);

        return ResponseEntity.status(201).body(null);
    }
    
}  

