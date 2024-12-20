package com.revature.p1.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.p1.dto.request.TicketRequest;
import com.revature.p1.entity.Reimbursement;
import com.revature.p1.entity.ReimbursementStatus;
import com.revature.p1.repository.ReimbursementRepository;
import com.revature.p1.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReimbursementService {
    ReimbursementRepository reimbursementRepository;
    UserRepository userRepository;
    UserService userService;

    

    public Reimbursement CreateReimbursement(TicketRequest request, String username){
        Reimbursement r = request.convertToReimbursement();
        r.setStatus(ReimbursementStatus.PENDING);
        r.setUser(userService.findbyUserName(username));
        return reimbursementRepository.save(r);
    }

    public Reimbursement approveReimbursement(long ticketId){
        Optional<Reimbursement> optR= reimbursementRepository.findById(ticketId);
        if (optR.isPresent()) {
            Reimbursement r = optR.get();
            r.setStatus(ReimbursementStatus.APPROVED);
            return reimbursementRepository.save(r);
        }
        throw new NoSuchElementException("There is no reimbursement ticket with id:"+ticketId);
    }
    
}
