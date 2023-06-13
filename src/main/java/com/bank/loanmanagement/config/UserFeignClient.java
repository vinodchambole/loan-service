package com.bank.loanmanagement.config;

import com.bank.loanmanagement.security.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userFeignClient", url = "http://localhost:8080", configuration = FeignClientConfig.class)
public interface UserFeignClient {

    @GetMapping("/users/email/{email}")
    ResponseEntity<User> getUserByEmail(@PathVariable String email);

}
