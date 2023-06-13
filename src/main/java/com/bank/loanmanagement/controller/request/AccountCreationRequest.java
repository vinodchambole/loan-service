package com.bank.loanmanagement.controller.request;

import com.bank.loanmanagement.entity.AccountType;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AccountCreationRequest {
    private String email;
    private String accountNumber;
    private AccountType accountType;
    private double amount;
}
