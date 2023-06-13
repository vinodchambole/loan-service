package com.bank.loanmanagement.controller;

import com.bank.loanmanagement.controller.request.LoanApprovalRequest;
import com.bank.loanmanagement.controller.request.LoanDisburseRequest;
import com.bank.loanmanagement.controller.request.LoanRequest;
import com.bank.loanmanagement.entity.Loan;
import com.bank.loanmanagement.entity.LoanStatus;
import com.bank.loanmanagement.security.user.User;
import com.bank.loanmanagement.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/apply")
    public ResponseEntity<String> getLoanStatusById(@AuthenticationPrincipal User user, @RequestBody LoanRequest loanRequest) {
        Loan loan = loanService.applyLoan(user, loanRequest);
        return ResponseEntity.accepted().body("Loan applied successfully with ID: " + loan.getId());
    }

    @GetMapping("/status")
    public List<Loan> getAllLoansByStatus(@RequestParam("status") String status) {
        return loanService.getAllLoansByStatus(LoanStatus.valueOf(status));
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<String> getLoanStatusById(@AuthenticationPrincipal User user, @PathVariable String id) {
        LoanStatus loan = loanService.getStatus(user, id);
        return ResponseEntity.ok().body("Loan application status: " + loan.getStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@AuthenticationPrincipal User user, @PathVariable String id) {
        Loan loan = loanService.getLoanById(user, id);
        return ResponseEntity.ok().body(loan);
    }

    @PatchMapping("/approve/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<LoanApprovalRequest> approveLoan(@AuthenticationPrincipal User user, @PathVariable String id, @RequestBody LoanApprovalRequest request) {
        Loan loan = loanService.approve(user, id, request);
        return ResponseEntity.ok().body(request);
    }

    @PutMapping("/disburse/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<Loan> disburseLoan(@AuthenticationPrincipal User user, @PathVariable String id, @RequestBody LoanDisburseRequest request) {
        Loan loan = loanService.disburse(user, id, request);
        return ResponseEntity.ok().body(loan);
    }

    // Other API endpoints for viewing loan status, making loan payments, managing loan accounts, etc.
}

