package com.bank.loanmanagement.exception;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(String s) {
        super(s);
    }
}
