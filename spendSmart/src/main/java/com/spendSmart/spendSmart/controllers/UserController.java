package com.spendSmart.spendSmart.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spendSmart.spendSmart.entities.Groups;
import com.spendSmart.spendSmart.entities.User;
import com.spendSmart.spendSmart.forms.GroupForm;
import com.spendSmart.spendSmart.repositories.GroupService;
import com.spendSmart.spendSmart.repositories.UserService;


import jakarta.validation.Valid;
import spendSmart.spendSmart.helpers.Helper;



@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model,Authentication authentication){
        Helper helper=new Helper();
        
        String email=helper.loggedInUser(authentication);
        
        User currentUser= userService.getUserByEmail(email).orElse(null);

        model.addAttribute("username", currentUser.getName().toString());
    }

    @RequestMapping("/dashboard")
    public String dashboard(Model model,Authentication authentication) {
         
        return "user/dashboard";
    }  
    @RequestMapping("/group")
    public String group(Model model,Authentication authentication) {

        GroupForm groupForm = new GroupForm();
        model.addAttribute("groupForm", groupForm);
        return "user/groups";
    }  

    @RequestMapping(value="/addGroup",method =RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute GroupForm  groupForm,Authentication authentication) {
        Helper helper=new Helper();
        
        String email=helper.loggedInUser(authentication);
        
        User currentUser= userService.getUserByEmail(email).orElse(null);

        groupForm.groupMembers.add(currentUser.getEmail());


        List<User> grpMembers=new ArrayList<>();

        for(String s:groupForm.getGroupMembers()){
            User grpMember=userService.getUserByEmail(s).orElse(null);
            if(grpMember!=null){
                grpMembers.add(grpMember);
            }
        }

        Groups grp=Groups.builder()
        .groupName(groupForm.getName())
        .description(groupForm.getDescription())
        .GroupMembers(grpMembers)
        .build();

        // groupForm->Group
        // store this group in the table 
        // take care of many to many dependency

        System.out.println(grp);

        groupService.saveGroup(grp);

        return "redirect:/user/group";
    }

    @RequestMapping("/challenges")
    public String challenges(Model model,Authentication authentication) {
        
        return "user/challenges";
    }  

}
