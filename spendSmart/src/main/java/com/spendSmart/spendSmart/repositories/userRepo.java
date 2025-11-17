package com.spendSmart.spendSmart.repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spendSmart.spendSmart.entities.User;

public interface userRepo extends JpaRepository<User, String> {


    Optional<User> findByEmail(String email);
    
    boolean existsByEmail(String email);

    @Query(value="SELECT group_id FROM users_groups WHERE user_id = :userId",nativeQuery = true)
    ArrayList<String> getGroupIdFromUserId(@Param("userId")String userId);

}
