package com.bank.loanmanagement.controller.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDisburseRequest {
    private String customer;
}
