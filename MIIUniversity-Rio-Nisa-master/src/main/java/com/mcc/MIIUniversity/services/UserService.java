/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.MIIUniversity.services;

import com.mcc.MIIUniversity.entities.User;
import com.mcc.MIIUniversity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gin
 */
@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;
    
    public boolean getLogin(String username, String password){
        boolean result = false;
        User user = userRepository.findLogin(username, password);
        
        if (user.getUsername() != null && user.getPassword() != null) {
            result = true;
        } else {
            result = false;
        }
        return result;
    } 
    
    public User getById(String username) {
        return userRepository.findById(username).get();
    }
}
