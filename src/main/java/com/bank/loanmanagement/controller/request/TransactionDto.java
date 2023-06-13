package com.bank.loanmanagement.controller.request;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Long fromAccountId;
    private Long toAccountId;
    private double amount;
    private String Status;
}
