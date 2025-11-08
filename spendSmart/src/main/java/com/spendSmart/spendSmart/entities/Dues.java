package com.spendSmart.spendSmart.entities;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    // @ManyToOne
    // private User owesTo;
    
    // @ManyToOne
    // private User owesFrom;
    
    // @ManyToOne
    // private Group records;


}