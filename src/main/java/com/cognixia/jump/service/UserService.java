package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepo;

    public List<User> getUsers() throws Exception{

        List<User> isEmpty = userRepo.findAll();

        // Check if there is a list of users available
        if(isEmpty.isEmpty()){
            // We can create custom exceptions and a global exception handler later
            throw new Exception("No current users");
        }

        return userRepo.findAll();
    }

    
    public User getUserById(int id) throws Exception{

        Optional<User> found = userRepo.findById(id);

        if(!found.isPresent()){
            throw new Exception("User with id " + id + " not found");
        }

        return found.get();
    }

    public User createUser(User user) throws Exception{  
        
        
        // we will add proper error checks later on 
        // for example we need to check if this user already exists in the 
        // database. For now we will just save
        Optional<User> exists = userRepo.findByUsername(user.getUsername());

        if(exists.isPresent()){
            throw new Exception("User " + user.getUsername() + " already exists");
        }


        return userRepo.save(user);
    }


}
