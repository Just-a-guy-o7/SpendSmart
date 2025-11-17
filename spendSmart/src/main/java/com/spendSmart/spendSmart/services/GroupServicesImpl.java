package com.spendSmart.spendSmart.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spendSmart.spendSmart.entities.Groups;
import com.spendSmart.spendSmart.entities.User;
import com.spendSmart.spendSmart.repositories.GroupRepo;
import com.spendSmart.spendSmart.repositories.GroupService;

import com.spendSmart.spendSmart.helpers.resourceNotFoundException;

@Service
public class GroupServicesImpl implements GroupService {

    @Autowired
    private GroupRepo groupRepo;

    @Override
    public Groups saveGroup(Groups groups) {
        String groupID = UUID.randomUUID().toString();
        groups.setGroupId(groupID);
        Groups savedgrp=groupRepo.save(groups);
        for(User u:groups.getGroupMembers()){
            groupRepo.enterUserRelatedToGroup(u.getUserId(), groupID);
        }
        return savedgrp;
    }

    @Override
    public Optional<Groups> getGroupById(String id) {
        return groupRepo.findById(id);
    }

    @Override
    public Optional<Groups> updateGroup(Groups groups) {
        Groups grpInDB = groupRepo.findById(groups.getGroupId()).orElseThrow(() -> new resourceNotFoundException());
        grpInDB.setGroupName(groups.getGroupName());
        grpInDB.setDescription(groups.getDescription());

        return Optional.ofNullable(groupRepo.save(grpInDB));
    }

    @Override
    public void deleteGroup(String id) {

        Groups grpInDB = groupRepo.findById(id).orElseThrow(() -> new resourceNotFoundException());
        groupRepo.delete(grpInDB);
    }

    @Override
    public boolean isGroupExist(String id) {
        Groups grpInDB = groupRepo.findById(id).orElse(null);
        if (grpInDB != null) {
            return true;
        }
        return false;
    }

}
