package com.revature.p1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.p1.entity.Reimbursement;

public interface ReimbursementRepository extends JpaRepository<Reimbursement,Long>{
    
}
