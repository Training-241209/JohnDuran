package com.revature.p1.repository;

import com.revature.p1.entity.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    
    Optional<Role> findByName(String name);
}
