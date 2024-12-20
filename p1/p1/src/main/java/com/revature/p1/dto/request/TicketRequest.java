package com.revature.p1.dto.request;

import com.revature.p1.entity.Reimbursement;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class TicketRequest {
    
    
    private String description;
    @Min(value = 1)
    private double amount;

    public Reimbursement convertToReimbursement(){
        Reimbursement r = new Reimbursement();
        r.setDescription(description);
        r.setAmount(amount);
        return r;     
    }
}
