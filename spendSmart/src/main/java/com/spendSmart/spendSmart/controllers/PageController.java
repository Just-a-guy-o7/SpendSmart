package com.spendSmart.spendSmart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spendSmart.spendSmart.entities.Provider;
import com.spendSmart.spendSmart.entities.Roles;
import com.spendSmart.spendSmart.entities.User;
import com.spendSmart.spendSmart.forms.UserForm;
import com.spendSmart.spendSmart.repositories.UserService;

import jakarta.validation.Valid;



@Controller
public class PageController {


    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
    @RequestMapping("/")
    public String home2() {
        System.out.println("Google client ID : "+System.getenv("GOOGLE_CLIENT_ID"));
        return "loginpage";
    }
    @RequestMapping("/signUp")
    public String signUp(Model model) {
        UserForm userForm=new UserForm();
        model.addAttribute("userForm", userForm);
        return "signUpPage";
    }

    @RequestMapping(value="/do-register",method =RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult) {
        if(rBindingResult.hasErrors()){
            return "signUpPage";
        }
        
        System.out.println(userForm);


        User userToSave=User.builder()
        .name(userForm.getFullName())
        .email(userForm.getEmail())
        .password(userForm.getPassword())
        .isEnabled(true)
        .provider(Provider.LOCAL)
        .role(Roles.USER)
        .userId(userForm.email)
        .build();
        userService.saveUser(userToSave);

        return "redirect:/login";
    }
    @RequestMapping("/login")
    public String login() {
        return "loginpage";
    }
    @RequestMapping("/authenticate")
    public String auth() {
        return "redirect:user/dashboard";
    }
}
