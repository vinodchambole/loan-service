package com.bank.loanmanagement.config;

import com.bank.loanmanagement.controller.request.AccountCreationRequest;
import com.bank.loanmanagement.controller.request.TransactionDto;
import com.bank.loanmanagement.controller.request.TransferRequest;
import com.bank.loanmanagement.controller.response.AccountCreationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "accountFeignClient", url = "http://localhost:8081", configuration = FeignClientConfig.class)
public interface AccountFeignClient {

    @PostMapping("/accounts")
    ResponseEntity<AccountCreationResponse> createAccount(@RequestBody AccountCreationRequest accountCreationRequest);

    @PostMapping("/transactions/transfer-balance")
    ResponseEntity<TransactionDto> transferBalance(@RequestBody TransferRequest request);
}
