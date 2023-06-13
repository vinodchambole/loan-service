package com.bank.loanmanagement.controller.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanRequest {

    private String userName;
    private double loanAmount;
    private String purpose;
    private boolean creditCheckRequired;
    private boolean collateralRequired;
    private double rateOfInterest;
    private int tenure;
}
