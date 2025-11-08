package com.spendSmart.spendSmart.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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


    // @ManyToOne
    // private User User;
    // @ManyToOne
    // private Group Group;
    // @ManyToOne
    // private ExpenseCategories Category;

}

