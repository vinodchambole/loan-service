package com.bank.loanmanagement.entity;

import jakarta.persistence.*;
import lombok.*;


@Builder
@Setter
@Getter
@Entity
@Table(name = "loans")
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String borrower;

    @Column(name = "loan_amount")
    private double loanAmount;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "credit_check_required")
    private boolean creditCheckRequired;

    @Column(name = "collateral_required")
    private boolean collateralRequired;

    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    private String message;

    private Double emi;

    private Double rateOfInterest;

    private Integer tenure;
}
