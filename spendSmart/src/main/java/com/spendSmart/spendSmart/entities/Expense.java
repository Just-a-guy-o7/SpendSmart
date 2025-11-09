package com.spendSmart.spendSmart.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="Expense")
@Table(name="Expense")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Expense {
    @Id
    private String ExpenseID;
    @Column(length = 1000)
    private String Description;
    private String ImageURL;
    @Column(length = 1000)
    private String ExtractedText;
    private double Amount;


    @ManyToOne
    private User ExpenseOfUser;
   
    @ManyToOne
    private Group Group;
    //check
    @ManyToOne
    private ExpenseCategories Category;
    //check
}

