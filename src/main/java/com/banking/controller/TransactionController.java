package com.banking.controller;

import com.banking.dto.response.TransactionResponse;
import com.banking.security.JwtUtil;
import com.banking.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final JwtUtil jwtUtil;

    @PostMapping("/withdraw")
    public TransactionResponse withdraw(
            @RequestParam String accountNumber,
            @RequestParam BigDecimal amount,
            @RequestParam String description,
            HttpServletRequest httpRequest) {
        
        String token = httpRequest.getHeader("Authorization").substring(7);
        UUID userId = UUID.fromString(jwtUtil.extractUserId(token));
        return transactionService.withdraw(userId, accountNumber, amount, description);
    }
}
