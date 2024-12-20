package com.revature.p1.dto.response;

import com.revature.p1.entity.Reimbursement;

import lombok.Data;

@Data
public class TicketResponse {
    private long id;
   
    private String description;
   
    private double amount;

    private String status;

    private String user;

    public TicketResponse(Reimbursement r){
        id = r.getId();
        description = r.getDescription();
        amount = r.getAmount();
        status = r.getStatus().toString();
        user = r.getUser().getUsername();
    }
}
