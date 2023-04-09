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

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

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
    @CrossOrigin
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) throws ResourceNotFoundException {

        User found = userService.getUserById(id);

        return ResponseEntity.status(200).body(found);
    }

    // creating a user
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) throws UserExistsException{
        
        
        user.setId(null);
        user.setPassword(encoder.encode(user.getPassword()));

        
        User created = userService.createUser(user);
        return ResponseEntity.status(201).body(created);
    }

    // Updating payment method
    @PatchMapping("/user/{id}")
    public ResponseEntity<?> updatePayment(@Valid @PathVariable int id, @RequestBody String paymentMethod) throws ResourceNotFoundException{

        String updated = userService.updatePayment(id, paymentMethod);

        return ResponseEntity.status(200).body(updated);
    }


}
