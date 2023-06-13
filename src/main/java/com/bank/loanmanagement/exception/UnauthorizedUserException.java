package com.bank.loanmanagement.exception;

public class UnauthorizedUserException extends RuntimeException {
    public UnauthorizedUserException(String s) {
        super(s);
    }
}
