package com.cognixia.jump.service;

import java.util.Optional;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognixia.jump.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        


        Optional<User> userFound = userRepo.findByUsername(username);

        if(userFound.isEmpty()){
            throw new UsernameNotFoundException(username);
        }

        return new MyUserDetails(userFound.get());
    }
    
    
}
