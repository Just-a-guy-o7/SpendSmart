package com.spendSmart.spendSmart.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spendSmart.spendSmart.entities.Groups;
import com.spendSmart.spendSmart.entities.User;
import com.spendSmart.spendSmart.helpers.Helper;
import com.spendSmart.spendSmart.repositories.GroupService;
import com.spendSmart.spendSmart.repositories.UserService;

@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    private UserService userService;
    @Autowired 
    private GroupService groupService;
    @Autowired
    private Helper helper;


    @GetMapping("/getGroups")
    public ArrayList<Groups> getGroups() {
        User currentUser=helper.loggedInUser();
        ArrayList<String> groupsId=userService.getGroupIdByUserId(currentUser.getUserId());
        ArrayList<Groups> groups=new ArrayList<>();
        for(String s:groupsId){
            System.out.println(s);
            groups.add(groupService.getGroupById(s).orElse(null));
        }
        return groups;
    }
}
