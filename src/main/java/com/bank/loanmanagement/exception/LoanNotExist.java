package com.bank.loanmanagement.exception;

public class LoanNotExist extends RuntimeException {
    public LoanNotExist(String s) {
        super(s);
    }
}
