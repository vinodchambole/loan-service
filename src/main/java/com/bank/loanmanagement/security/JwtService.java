package com.bank.loanmanagement.security;

import com.bank.loanmanagement.feignClient.AuthFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Autowired
    private AuthFeignClient authFeignClient;

    public UserDetails isTokenValid(String token) {
        return authFeignClient.validateToken(token);
    }
}
