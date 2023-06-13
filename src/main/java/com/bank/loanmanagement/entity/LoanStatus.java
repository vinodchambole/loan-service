package com.bank.loanmanagement.entity;


public enum LoanStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    DISBURSED("Disbursed"),
    COMPLETED("Completed"),
    DEFAULTED("Defaulted");

    private final String status;

    LoanStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
