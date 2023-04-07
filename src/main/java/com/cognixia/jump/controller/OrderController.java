package com.cognixia.jump.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.MenuItem;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.service.OrderService;


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
        
        // List<OrderMenuItem> menuItems = orderService.getOrderById(id);
        

        return ResponseEntity.status(201).body(null);
    }

    @PutMapping("/order")
    public ResponseEntity<?> addItem(@RequestBody MenuItem menuItem) {

        return ResponseEntity.status(201).body(null);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> removeMenuItem(@PathParam(value="menuItemId") int menuItemId, @PathParam(value="orderId") int orderId) throws ResourceNotFoundException{

        orderService.deleteItem(menuItemId, orderId);

        return ResponseEntity.status(200).body("Deleted item with id = " + menuItemId + "from order #" + menuItemId);
    }
    
}  

