package com.revature.p1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.p1.dto.request.TicketRequest;
import com.revature.p1.dto.response.TicketResponse;
import com.revature.p1.security.JwtTokenProvider;
import com.revature.p1.service.ReimbursementService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
    ReimbursementService reimbursementService;   
    JwtTokenProvider tokenProvider;

    @PostMapping(value = "add")
    public ResponseEntity<TicketResponse> createTickect(@RequestBody TicketRequest r, HttpServletRequest  request) throws Exception {
        
        String Token = request.getHeader("Authorization");
        String username = tokenProvider.getUsername(Token);
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new TicketResponse(reimbursementService.CreateReimbursement(r,username)));             
    }

    @PatchMapping(value="approve/{ticketid}")
    public ResponseEntity<TicketResponse> approveTicket(@PathVariable long ticketid) {

        return ResponseEntity.ok(new TicketResponse(reimbursementService.approveReimbursement(ticketid))); 
    }
}
