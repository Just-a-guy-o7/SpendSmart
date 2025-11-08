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
}
