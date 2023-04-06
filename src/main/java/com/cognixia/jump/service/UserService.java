package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepo;

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public User createUser(User user){  
        
        // we will add proper error checks later on 
        // for example we need to check if this user already exists in the 
        // database. For now we will just save
        return userRepo.save(user);
    }


}
