package com.bank.loanmanagement.controller;

import com.bank.loanmanagement.security.user.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test-token")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String string(@AuthenticationPrincipal User user) {
        User user1 = user;
        return "Hello Manger";
    }
}
