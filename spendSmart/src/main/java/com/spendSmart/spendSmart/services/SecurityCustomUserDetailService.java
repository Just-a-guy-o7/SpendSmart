package com.spendSmart.spendSmart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.spendSmart.spendSmart.repositories.userRepo;


@Service
public class SecurityCustomUserDetailService implements UserDetailsService{

    @Autowired
    private userRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     
        return userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User Not Found with email: "+username));
    }

}
