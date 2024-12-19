package com.revature.p1.service;

import org.springframework.stereotype.Service;

import com.revature.p1.entity.Reimbursement;
import com.revature.p1.repository.ReimbursementRepository;

@Service
public class ReimbursementService {
    ReimbursementRepository reimbursementRepository;    

    public ReimbursementService(ReimbursementRepository reimbursementRepository) {
        this.reimbursementRepository = reimbursementRepository;
    }

    public Reimbursement CreateReimbursement(Reimbursement reimbursement){

        return reimbursementRepository.save(reimbursement);

    }

    
     

    
}
