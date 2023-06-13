package com.bank.loanmanagement.controller.response;

import com.bank.loanmanagement.entity.AccountType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountCreationResponse {
    private Long accountId;
    private String email;
    private String accountNumber;
    private AccountType accountType;
    private String transactionPassword;
}
