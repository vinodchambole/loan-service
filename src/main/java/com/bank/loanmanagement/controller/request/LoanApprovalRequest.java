package com.bank.loanmanagement.controller.request;

import com.bank.loanmanagement.entity.LoanStatus;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoanApprovalRequest {
    private LoanStatus loanStatus;
    private String message;
    private String customer;
}
