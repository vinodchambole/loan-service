package com.bank.loanmanagement.service;

import com.bank.loanmanagement.config.UserFeignClient;
import com.bank.loanmanagement.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserFeignClient feignClient;

    public User getUserByEmail(String userName) {
        ResponseEntity<User> byEmail = feignClient.getUserByEmail(userName);
        return byEmail.getBody();
    }
}
