package com.bank.loanmanagement.controller;

import com.bank.loanmanagement.controller.request.LoanRequest;
import com.bank.loanmanagement.entity.Loan;
import com.bank.loanmanagement.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/apply")
    public ResponseEntity<String> applyLoan(@RequestBody LoanRequest loanRequest) {
        Loan loan = loanService.applyLoan(loanRequest);
        return ResponseEntity.ok("Loan applied successfully with ID: " + loan.getId());
    }

    // Other API endpoints for viewing loan status, making loan payments, managing loan accounts, etc.
}

