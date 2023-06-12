package com.bank.loanmanagement.service;

import com.bank.loanmanagement.controller.request.LoanRequest;
import com.bank.loanmanagement.entity.Loan;
import com.bank.loanmanagement.repository.LoanRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan applyLoan(LoanRequest loanRequest) {
        Loan loan = new Loan(loanRequest.getLoanAmount(), loanRequest.getPurpose(),
                loanRequest.isCreditCheckRequired(), loanRequest.isCollateralRequired());
        return loanRepository.save(loan);
    }
}
