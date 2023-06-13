package com.bank.loanmanagement.repository;

import com.bank.loanmanagement.entity.Loan;
import com.bank.loanmanagement.entity.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByLoanStatus(LoanStatus loanStatus);
}
