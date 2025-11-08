package com.spendSmart.spendSmart.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="User")
@Table(name="Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

       
    @Id
    @Column(name = "user_id")
    private String userId;  // Changed from UserID
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "profile_picture_url", length = 1000)
    private String profilePictureUrl;
    
    @Column(name = "about", length = 1000)
    private String about;
    
    @Column(name = "is_enabled")
    private boolean isEnabled;
    
    @Column(name = "is_email_verified")
    private boolean isEmailVerified;
    
    @Column(name = "provider")
    private String provider;
    
    @Column(name = "provider_id")
    private String providerId;
}
