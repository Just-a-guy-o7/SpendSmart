package com.spendSmart.spendSmart.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spendSmart.spendSmart.entities.User;
import com.spendSmart.spendSmart.repositories.UserService;
import com.spendSmart.spendSmart.repositories.userRepo;

import spendSmart.spendSmart.helpers.resourceNotFoundException;

@Service
public class UserServicesImpl implements UserService{

    @Autowired
    private userRepo userRepo;
    @Override
    public User saveUser(User user) {
        return userRepo.save(user);    
    }

    @Override
    public Optional<User> getUserById(String id) {
        
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User userInDB=userRepo.findById(user.getUserId()).orElseThrow(()->new resourceNotFoundException());
        userInDB.setName(user.getName());
        userInDB.setEmail(user.getEmail());
        userInDB.setPassword(user.getPassword());

        return Optional.ofNullable(userRepo.save(userInDB));

    }

    @Override
    public void deleteUser(String id) {
        User userInDB=userRepo.findById(id).orElseThrow(()->new resourceNotFoundException());
        userRepo.delete(userInDB);
    }

    @Override
    public boolean isUserExist(String id) {
        User userInDB=userRepo.findById(id).orElse(null);
        if(userInDB!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User userInDB=userRepo.findByEmail(email).orElse(null);
        if(userInDB!=null){
            return true;
        }
        return false;
        
    }

}
