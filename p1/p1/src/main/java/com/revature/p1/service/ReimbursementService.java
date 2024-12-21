package com.revature.p1.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.p1.dto.request.TicketRequest;
import com.revature.p1.entity.Reimbursement;
import com.revature.p1.entity.ReimbursementStatus;
import com.revature.p1.entity.User;
import com.revature.p1.exception.custom.ForbiddenException;
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
    

    public Reimbursement updateReimbursementStatus(long ticketId, ReimbursementStatus status){
        Optional<Reimbursement> optR= reimbursementRepository.findById(ticketId);
        if (optR.isPresent()) {
            Reimbursement r = optR.get();
            r.setStatus(status);
            return reimbursementRepository.save(r);
        }
        throw new NoSuchElementException("There is no reimbursement ticket with id:"+ticketId);
    }

    public Reimbursement updateReimbursementDescription(long ticketId, String description){
        Optional<Reimbursement> optR= reimbursementRepository.findById(ticketId);
        if (optR.isPresent()) {
            Reimbursement r = optR.get();
            r.setDescription(description);;
            return reimbursementRepository.save(r);
        }
        throw new NoSuchElementException("There is no reimbursement ticket with id:"+ticketId);
    }

    public void isUserAuthorizedtoUpdateTicket(String username, long ticketId){        
        Optional<Reimbursement> optR= reimbursementRepository.findById(ticketId);
        if (optR.isPresent()) {
            Reimbursement r = optR.get();
            if (!r.getUser().getUsername().equals(username)) {
                throw new ForbiddenException("Since you are not the author, you are not authorized to update the ticket with id:"+ticketId);
            }else if(!r.getStatus().equals(ReimbursementStatus.PENDING)){
                throw new ForbiddenException("Tickets can't be updated after approval");
            }
        }else{
            throw new NoSuchElementException("There is no reimbursement ticket with id:"+ticketId);
        }
    }

    public boolean softDeleteReimbursement(long ticketId){
        Optional<Reimbursement> optR= reimbursementRepository.findByIdAndDeletedFalse(ticketId);
        if (optR.isPresent()) {
            Reimbursement r = optR.get();
            r.setDeleted(true);
            reimbursementRepository.save(r);
            return true;
        }
        return false;
    }

    public List<Reimbursement> getReimbursementsFilter(String username, boolean filterPending){
        if (userService.getRolesNamesByUsername(username).contains("MANAGER")) {
            return getReimbursementsByStatus(filterPending);            
        }
        else{
            User user = userService.findbyUserName(username);
            return getReimbursementsByUserAndByStatus(user, filterPending);
        }
    }

    private List<Reimbursement> getReimbursementsByStatus(boolean pendingFilter){
        if (pendingFilter) {
            return reimbursementRepository.findByStatusAndDeletedFalse(ReimbursementStatus.PENDING);
        }else{
            return reimbursementRepository.findByDeletedFalse();
        }
    }

    private List<Reimbursement> getReimbursementsByUserAndByStatus(User user,boolean filterPending){
        if (filterPending) {
            return reimbursementRepository.findByStatusAndUserAndDeletedFalse(ReimbursementStatus.PENDING,user);
        }else{
            return reimbursementRepository.findByUserAndDeletedFalse(user);
        }
    }

    
}
