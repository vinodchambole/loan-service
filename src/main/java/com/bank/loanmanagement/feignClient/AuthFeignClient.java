package com.bank.loanmanagement.feignClient;

import com.bank.loanmanagement.security.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authFeignClient", url = "http://localhost:8080") // Replace with your API URL
public interface AuthFeignClient {

    @PostMapping("/validate-token")
    User validateToken(@RequestHeader("Authorization") String authorizationHeader);
}
