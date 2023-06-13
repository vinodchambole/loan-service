package com.bank.loanmanagement.service;

import com.bank.loanmanagement.config.AccountFeignClient;
import com.bank.loanmanagement.controller.request.AccountCreationRequest;
import com.bank.loanmanagement.controller.request.TransferRequest;
import com.bank.loanmanagement.controller.response.AccountCreationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountFeignClient feignClient;

    public AccountCreationResponse createAccount(AccountCreationRequest accountCreationRequest) {
        ResponseEntity<AccountCreationResponse> account = feignClient.createAccount(accountCreationRequest);
        return account.getBody();
    }

    public void transferBalance(TransferRequest request) {
        feignClient.transferBalance(request);
    }
}
