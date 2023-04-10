package com.cognixia.jump.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.MenuItem;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.model.OrderMenuItem;
import com.cognixia.jump.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api")
@Tag(name = "Order", description = "The API for managing Orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    // Gets the history of orders
    @Operation(summary="Get all the orders", description="This endpoint will allow admins to view all the orders")
    @CrossOrigin
    @GetMapping("/orders")
    public ResponseEntity<?> getOrders(){

        // error check on frontend?
        List<Order> orders = orderService.getOrders();

        return ResponseEntity.status(200).body(orders);
    }

    // Gets a specific order
    @Operation(summary="Get order by ID", description="Given an order id, this endpoint will return the items in the order")
    @CrossOrigin
    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable int id) throws ResourceNotFoundException {
        
        Order items = orderService.getOrderById(id);

        return ResponseEntity.status(200).body(items);
    }

    // Create an order
    @Operation(summary="Create an Order", description="Allows users to create an order")
    @CrossOrigin
    @PostMapping("/order/{userId}")
    public ResponseEntity<?> createOrder(@Valid @PathVariable int userId) throws Exception{

        Order created = orderService.createOrder(userId);

        return ResponseEntity.status(201).body(created);

    }

    @Operation(summary="Add menu items to order", description="Allows users to add items to an existing order created")
    @CrossOrigin
    @PostMapping("/order/add")
    public ResponseEntity<?> addItem(@PathParam(value = "menuItemId")int menuItemId, @PathParam(value = "orderId")int orderId) throws ResourceNotFoundException {

        OrderMenuItem menuItem = orderService.addItem(menuItemId, orderId);

        return ResponseEntity.status(201).body(menuItem);
    }

    @Operation(summary="Remove menu items to order", description="Allows users to remove item from an existing order")
    @CrossOrigin
    @DeleteMapping("/order/remove")
    public ResponseEntity<?> removeMenuItem(@PathParam(value="menuItemId") int menuItemId, @PathParam(value="orderId") int orderId) throws ResourceNotFoundException{

        orderService.deleteItem(menuItemId, orderId);

        return ResponseEntity.status(200).body("Deleted item with id = " + menuItemId + "from order #" + orderId);
    }
    
}  

