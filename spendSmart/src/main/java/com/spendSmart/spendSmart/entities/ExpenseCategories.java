package com.spendSmart.spendSmart.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="ExpenseCategories")
@Table(name="ExpenseCategories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseCategories {
    @Id
    private String CategoryID;
    private String CategoryName;
    @Column(length = 1000)
    private String Description;
    @OneToMany(mappedBy = "Category",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Expense> expenses=new ArrayList<>();
    //check
}
