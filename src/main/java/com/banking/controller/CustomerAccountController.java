package com.banking.controller;

import com.banking.dto.request.AccountCreateRequest;
import com.banking.dto.response.AccountResponse;
import com.banking.dto.response.PageResponse;
import com.banking.security.JwtUtil;
import com.banking.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class CustomerAccountController {

    private final AccountService accountService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public AccountResponse openAccount(@Valid @RequestBody AccountCreateRequest request, HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization").substring(7);
        UUID userId = UUID.fromString(jwtUtil.extractUserId(token));
        return accountService.openAccount(userId, request);
    }

    @GetMapping
    public PageResponse<AccountResponse> getMyAccounts(Pageable pageable, HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization").substring(7);
        UUID userId = UUID.fromString(jwtUtil.extractUserId(token));
        return accountService.getMyAccounts(userId, pageable);
    }

    @GetMapping("/{accountNumber}")
    public AccountResponse getAccountDetails(@PathVariable String accountNumber) {
        return accountService.getAccountDetails(accountNumber);
    }
}
