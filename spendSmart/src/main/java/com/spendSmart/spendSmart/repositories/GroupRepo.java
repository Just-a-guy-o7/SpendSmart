package com.spendSmart.spendSmart.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spendSmart.spendSmart.entities.Groups;

import jakarta.transaction.Transactional;

public interface GroupRepo extends JpaRepository<Groups, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users_groups (user_Id,group_Id) VALUES(:userId,:groupId);",nativeQuery = true)
    void enterUserRelatedToGroup(@Param("userId") String userID,@Param("groupId")String groupID);  
    
    
}
