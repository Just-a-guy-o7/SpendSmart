package com.spendSmart.spendSmart.repositories;

import java.util.ArrayList;
import java.util.Optional;

import com.spendSmart.spendSmart.entities.Groups;
import com.spendSmart.spendSmart.entities.User;

public interface GroupService {
 

    Groups saveGroup(Groups groups);

    Optional<Groups> getGroupById(String id);

    Optional<Groups> updateGroup(Groups groups);

    void deleteGroup(String id);

    boolean isGroupExist(String id);

}
