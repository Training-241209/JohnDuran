package com.revature.p1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.p1.dto.JwtAuthResponse;
import com.revature.p1.dto.LoginRequest;
import com.revature.p1.dto.SignUpRequest;
import com.revature.p1.entity.Reimbursement;
import com.revature.p1.entity.User;
import com.revature.p1.service.AuthService;
import com.revature.p1.service.ReimbursementService;
import com.revature.p1.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
public class MainController {
    UserService userService;
    ReimbursementService reimbursementService;
    AuthService authService;
    
    

    @GetMapping(value = "/test")
    public String getMessages(){
        return "Is working!!";
    }

    @GetMapping(value = "/test2")
    public String getMessages2(){
        return "Is working 2!!";
    }

    @PostMapping(value = "/auth/user")
    public ResponseEntity<User> signUp(@Valid @RequestBody SignUpRequest user)  throws Exception{
         
        return ResponseEntity.ok(userService.registerUser(user));       
    }

    @PostMapping(value = "/auth/login")
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody LoginRequest user) throws Exception {
         
        String token = authService.login(user);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);    
    }

    @PostMapping(value = "reimbursement")
    public ResponseEntity<Reimbursement> CreateReimbursement(@RequestBody Reimbursement reimbursement) throws Exception {
        
        return ResponseEntity.ok(reimbursementService.CreateReimbursement(reimbursement));
             
    }


    




    


}
