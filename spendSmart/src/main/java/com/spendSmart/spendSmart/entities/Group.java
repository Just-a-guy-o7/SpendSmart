package com.spendSmart.spendSmart.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.util.Lazy;

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

@Entity(name="Group")
@Table(name="Groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Group {

    @Id
    @Column(name = "group_id")
    private String groupId;

    @ManyToMany(mappedBy = "Groups")
    private List<User> GroupMembers=new ArrayList<>();
    
    @OneToMany(mappedBy = "PartOfGroup",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Dues> records=new ArrayList<>();
    
    @OneToMany(mappedBy = "Group",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Expense> Expenses=new ArrayList<>();
    
}
