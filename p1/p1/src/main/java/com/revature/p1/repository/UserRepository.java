package com.revature.p1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.p1.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
    User findUserByUsernameAndPasswordAndDeletedFalse(String username, String password);

    Optional<User> findUserByUsernameAndDeletedFalse(String username);

    boolean existsByUsername(String username); 
    
    List<User> findByDeletedFalse();

    Optional<User> findByIdAndDeletedFalse(long id);

}
