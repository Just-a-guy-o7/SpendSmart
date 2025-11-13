package com.spendSmart.spendSmart.entities;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="groupsUsers")
@Table(name="groupsUsers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Groups {

    @Id
    @Column(name = "groupId")
    private String groupId;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "Group_name")
    private String groupName;

    @ManyToMany(mappedBy = "Groups")
    private List<User> GroupMembers=new ArrayList<>();
    
    @OneToMany(mappedBy = "PartOfGroup",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Dues> records=new ArrayList<>();
    
    @OneToMany(mappedBy = "Group",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Expense> Expenses=new ArrayList<>();
    
}
