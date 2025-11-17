package com.spendSmart.spendSmart.helpers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spendSmart.spendSmart.entities.User;
import com.spendSmart.spendSmart.repositories.UserService;

@Component
public class Helper {

    @Autowired
    private UserService userService;
    
    public User loggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email="";
        if(authentication instanceof OAuth2AuthenticationToken){
            var oauth2user=(OAuth2User) authentication.getPrincipal();
            email=oauth2user.getAttribute("email").toString();
        }
        else{
            email=authentication.getName().toString();
        }

        
        return userService.getUserByEmail(email).orElse(null);
    }
}
