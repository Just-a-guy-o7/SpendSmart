package com.spendSmart.spendSmart.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="Dues")
@Table(name="Dues")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dues {
    @Id
    private String DuesID;
    private String ammount;

    @ManyToOne
    private User OwesTo;
  
    @ManyToOne
    private User OwesFrom;
    //check
    @ManyToOne
    private Group PartOfGroup;
    //check

}