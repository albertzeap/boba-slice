package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.exception.UserExistsException;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepo;

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    
    public User getUserById(int id) throws ResourceNotFoundException {

        Optional<User> found = userRepo.findById(id);

        if(!found.isPresent()){
            throw new ResourceNotFoundException("User", id);
        }

        return found.get();
    }

    public User createUser(User user) throws UserExistsException{  
        
        Optional<User> exists = userRepo.findByUsername(user.getUsername());

        if(exists.isPresent()){
            throw new UserExistsException("User", user.getUsername());
        }


        return userRepo.save(user);
    }


}
