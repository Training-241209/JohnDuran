package com.revature.p1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Reimbursement {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
   
    private String description;
   
    private double amount;

    @Enumerated(EnumType.STRING)
    private ReimbursementStatus status = ReimbursementStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;

    private boolean deleted = false;
}


