package com.spendSmart.spendSmart.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.util.Lazy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "profile_picture_url", length = 1000)
    private String profilePictureUrl;
    
    @Column(name = "is_enabled")
    private boolean isEnabled;
    
    @Column(name = "is_email_verified")
    private boolean isEmailVerified;
    
    @Enumerated
    @Column(name = "provider")
    private Provider provider;
    
    @Column(name = "provider_id")
    private String providerId;
    
    @Enumerated
    @Column(name = "role")
    private Roles role;


    @OneToMany(mappedBy = "User",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Challenges> challenges=new ArrayList<>();


    @OneToMany(mappedBy = "ExpenseOfUser",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Expense> expenses=new ArrayList<>();

    
    @OneToMany(mappedBy = "OwesFrom",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Dues> owesFrom=new ArrayList<>();

    
    @OneToMany(mappedBy = "OwesTo",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Dues> owesTo=new ArrayList<>();


    @ManyToMany
    @JoinTable(name = "Users_Groups",
        joinColumns=@JoinColumn(name="user_id")  ,
        inverseJoinColumns = @JoinColumn(name="groupId")  
    )
    private List<Group> Groups=new ArrayList<>();
  
}
