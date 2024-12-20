package com.revature.p1.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import com.revature.p1.entity.ReimbursementStatus;
import com.revature.p1.security.JwtTokenProvider;
import com.revature.p1.service.ReimbursementService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
@RequestMapping("/ticket")
public class TicketController {
    ReimbursementService reimbursementService;
    JwtTokenProvider tokenProvider;

    @PostMapping()
    public ResponseEntity<TicketResponse> createTicket(@RequestBody TicketRequest r, HttpServletRequest request)
            throws Exception {

        String Token = request.getHeader("Authorization");
        String username = tokenProvider.getUsername(Token);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new TicketResponse(reimbursementService.CreateReimbursement(r, username)));
    }

    @GetMapping()
    public ResponseEntity<List<TicketResponse>> getTickets(@RequestParam(defaultValue = "false") boolean filterPending, HttpServletRequest request) {
        String Token = request.getHeader("Authorization");
        String username = tokenProvider.getUsername(Token);
        return ResponseEntity.ok(
                reimbursementService.getReimbursementsFilter(username,filterPending)
                        .stream()
                        .map(r -> new TicketResponse(r))
                        .collect(Collectors.toList()));
    }

    @PatchMapping(value = "{ticketid}/approve")
    public ResponseEntity<TicketResponse> approveTicket(@PathVariable long ticketid) {

        return ResponseEntity.ok(new TicketResponse(reimbursementService.updateStatusReimbursement(ticketid, ReimbursementStatus.APPROVED)));
    }
    @PatchMapping(value = "{ticketid}/deny")
    public ResponseEntity<TicketResponse> denyTicket(@PathVariable long ticketid) {

        return ResponseEntity.ok(new TicketResponse(reimbursementService.updateStatusReimbursement(ticketid, ReimbursementStatus.DENIED)));
    }
}
