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

@Entity(name="Challenges")
@Table(name="Challenges")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Challenges {
    @Id
    private String ChallengeID;
    @Column(nullable=false)
    private String ChallengeName;
    @Column(length = 1000 , nullable=false)
    private String Description;

    @ManyToOne
    private User User;
}
