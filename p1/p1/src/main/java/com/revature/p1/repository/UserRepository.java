package com.revature.p1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.p1.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
    User findUserByUsernameAndPassword(String username, String password);

    Optional<User> findUserByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndPassword(String username,String password);


    

}
