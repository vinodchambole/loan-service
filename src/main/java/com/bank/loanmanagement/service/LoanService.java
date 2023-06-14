package com.bank.loanmanagement.service;

import com.bank.loanmanagement.controller.request.*;
import com.bank.loanmanagement.controller.response.AccountCreationResponse;
import com.bank.loanmanagement.entity.AccountType;
import com.bank.loanmanagement.entity.Loan;
import com.bank.loanmanagement.entity.LoanStatus;
import com.bank.loanmanagement.exception.LoanNotFoundException;
import com.bank.loanmanagement.exception.UnauthorizedUserException;
import com.bank.loanmanagement.repository.LoanRepository;
import com.bank.loanmanagement.security.user.Role;
import com.bank.loanmanagement.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private AccountService accountService;
    @Value("${admin.account.id}")
    private Long adminAccountId;
    @Autowired
    private UserService userService;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan applyLoan(User user, LoanRequest loanRequest) {

        User userByEmail = userService.getUserByEmail(loanRequest.getUserName());
        if (user.getRole().equals(Role.ADMIN) || (Objects.equals(loanRequest.getUserName(), userByEmail.getUsername()))) {

            Loan loan = Loan.builder().loanStatus(LoanStatus.PENDING)
                    .loanAmount(loanRequest.getLoanAmount())
                    .purpose(loanRequest.getPurpose())
                    .creditCheckRequired(loanRequest.isCreditCheckRequired())
                    .collateralRequired(loanRequest.isCollateralRequired())
                    .borrower(loanRequest.getUserName())
                    .rateOfInterest(loanRequest.getRateOfInterest())
                    .tenure(loanRequest.getTenure())
                    .build();
//            Customer customer = new Customer(account.getEmail());
//            Account build = Account.builder()
//                    .accountNumber(account.getAccountNumber())
//                    .accountType(account.getAccountType())
//                    .createdAt(LocalDateTime.now())
//                    .customer(customer)
//                    .build();

//            customerRepository.save(customer);
            // accountRepository.save(build);
            return loanRepository.save(loan);
        } else {
            throw new UnauthorizedUserException("Unauthorized user.");
        }
    }

    private String generateAccountNumber() {
        Random random = new Random();
        long randomNumber = random.nextLong() % 10000000000L;
        if (randomNumber < 0) {
            randomNumber += 10000000000L;
        }
        return String.valueOf(randomNumber);
    }

    public LoanStatus getStatus(User user, String id) {
        return loanRepository.findById(Long.parseLong(id)).map(Loan::getLoanStatus)
                .orElseThrow(() -> new LoanNotFoundException("Loan not exist by id: " + id));
    }

    public Loan getLoanById(User user, String id) {
        return loanRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new LoanNotFoundException("Loan not exist by id: " + id));
    }

    public Loan approve(User user, String id, LoanApprovalRequest request) {
        return loanRepository.findById(Long.parseLong(id))
                .map(loan -> {
                    loan.setLoanStatus(request.getLoanStatus());
                    loan.setMessage(request.getMessage());
                    Loan save = loanRepository.save(loan);
                    return save;
                }).orElseThrow(() -> new LoanNotFoundException("Loan not exist by id: " + id));
    }

    public Loan disburse(User user, String id, LoanDisburseRequest request) {
        return loanRepository.findById(Long.parseLong(id))
                .map(loan -> {
                    if (loan.getLoanStatus() == LoanStatus.APPROVED) {
                        AccountCreationResponse account = accountService.createAccount(AccountCreationRequest.builder()
                                .email(request.getCustomer())
                                .accountType(AccountType.LOAN)
                                .accountNumber(generateAccountNumber())
                                .amount(loan.getLoanAmount())
                                .build());

                        TransferRequest transferRequest = new TransferRequest();
                        transferRequest.setFromAccountId(adminAccountId);
                        transferRequest.setToAccountId(account.getAccountId());
                        transferRequest.setAmount(loan.getLoanAmount());

                        accountService.transferBalance(transferRequest);
                        loan.setLoanStatus(LoanStatus.DISBURSED);

                        double emi = calculateEMI(loan.getLoanAmount(), loan.getRateOfInterest(), loan.getTenure());
                        loan.setEmi(emi);
                        return loanRepository.save(loan);
                    } else {
                        throw new RuntimeException("Loan status is not approved or invalid");
                    }
                }).orElseThrow(() -> new LoanNotFoundException("Loan not exist by id: " + id));
    }

    public double calculateEMI(double principal, double interestRate, int tenure) {
        double monthlyInterestRate = interestRate / (12 * 100);
        int numberOfPayments = tenure * 12;

        return (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
    }

    public List<Loan> getAllLoansByStatus(LoanStatus status) {
        return loanRepository.findByLoanStatus(status);
    }
}
