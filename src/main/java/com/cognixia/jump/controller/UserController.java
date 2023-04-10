package com.cognixia.jump.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.exception.UserExistsException;
import com.cognixia.jump.model.User;
import com.cognixia.jump.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "User", description = "The API for managing Users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Operation(summary="Get all users in user table", description="Gets all Users in user table from the food_db. Each user has a auto-incremented id, email, is enabled (set to 0), first name, last name, password, payment card, phone umber, user role, and username")
    @CrossOrigin
    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){

        List<User> users = userService.getUsers();
        
        // checks if users list is empty
        if(users.isEmpty()) {
        	return ResponseEntity.status(200).body("There are currenly no users!");
        }

        return ResponseEntity.status(200).body(users);
    }

    // getting the specific user
    @Operation(summary="Get a specific User by id", description="Get the user by id. the ID is created when a new user is registred. Each user ID is unique.")
    @CrossOrigin
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) throws ResourceNotFoundException {

        User found = userService.getUserById(id);

        return ResponseEntity.status(200).body(found);
    }

    // creating a user
    @Operation(summary="Create User in user table", description="Creates a user and inserts them into the food_db database. Each user has a auto-incremented id, email, is enabled (set to 0), first name, last name, password, payment card, phone umber, user role, and username")
    @CrossOrigin
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) throws UserExistsException{
        
        
        user.setId(null);
        user.setPassword(encoder.encode(user.getPassword()));

        
        User created = userService.createUser(user);
        return ResponseEntity.status(201).body(created);
    }

    // Updating payment method
    @Operation(summary="Update the user payment method", description="Updates the user payement details. The valid payment for card transactionsare 16-digit integers")
    @PatchMapping("/user/{id}")
    public ResponseEntity<?> updatePayment(@Valid @PathVariable int id, @RequestBody String paymentMethod) throws ResourceNotFoundException{

        String updated = userService.updatePayment(id, paymentMethod);

        return ResponseEntity.status(200).body(updated);
    }


}
