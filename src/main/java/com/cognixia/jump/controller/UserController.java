package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.User;
import com.cognixia.jump.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() throws Exception{

        List<User> users = userService.getUsers();

        return ResponseEntity.status(200).body(users);
    }

    // getting the specific user
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) throws Exception {

        User found = userService.getUserById(id);

        return ResponseEntity.status(200).body(found);
    }

    // creating a user
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user) throws Exception{
        
        
        user.setId(null);
        user.setPassword(encoder.encode(user.getPassword()));

        
        User created = userService.createUser(user);
        return ResponseEntity.status(201).body(created);
    }


}
