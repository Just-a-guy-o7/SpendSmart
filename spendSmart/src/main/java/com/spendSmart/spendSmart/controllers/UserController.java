package com.spendSmart.spendSmart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spendSmart.spendSmart.entities.User;
import com.spendSmart.spendSmart.repositories.UserService;

import spendSmart.spendSmart.helpers.Helper;



@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService UserService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model,Authentication authentication){
        Helper helper=new Helper();
        
        String email=helper.loggedInUser(authentication);
        
        User currentUser= UserService.getUserByEmail(email).orElse(null);

        model.addAttribute("username", currentUser.getName().toString());
    }

    @RequestMapping("/dashboard")
    public String dashboard(Model model,Authentication authentication) {
         
        return "user/dashboard";
    }  
}
