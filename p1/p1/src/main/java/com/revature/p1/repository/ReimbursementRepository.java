package com.revature.p1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.p1.entity.Reimbursement;
import com.revature.p1.entity.ReimbursementStatus;
import com.revature.p1.entity.User;

public interface ReimbursementRepository extends JpaRepository<Reimbursement,Long>{
    
    List<Reimbursement> findByStatusAndDeletedFalse(ReimbursementStatus status);
    List<Reimbursement> findByStatusAndUserAndDeletedFalse(ReimbursementStatus status, User user);
    List<Reimbursement> findByUserAndDeletedFalse(User user);
    List<Reimbursement> findByUser(User user);
    List<Reimbursement> findByDeletedFalse();
}
