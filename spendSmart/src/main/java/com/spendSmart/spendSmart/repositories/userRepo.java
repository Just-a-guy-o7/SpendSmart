package com.spendSmart.spendSmart.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spendSmart.spendSmart.entities.User;

public interface userRepo extends JpaRepository<User, String> {


    Optional<User> findByEmail(String email);
    
    boolean existsByEmail(String email);
}
