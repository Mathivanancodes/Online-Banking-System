package com.banking.controller;

import com.banking.dto.response.AccountResponse;
import com.banking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/accounts")
@RequiredArgsConstructor
public class AdminAccountController {

    private final AccountService accountService;

    @GetMapping("/{accountNumber}")
    public AccountResponse getAccountDetails(@PathVariable String accountNumber) {
        return accountService.getAccountDetails(accountNumber);
    }

    @PutMapping("/{accountNumber}/freeze")
    public void freezeAccount(@PathVariable String accountNumber) {
        accountService.freezeAccount(accountNumber);
    }

    @PutMapping("/{accountNumber}/unfreeze")
    public void unfreezeAccount(@PathVariable String accountNumber) {
        accountService.unfreezeAccount(accountNumber);
    }
}
