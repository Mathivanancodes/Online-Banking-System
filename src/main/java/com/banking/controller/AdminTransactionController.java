package com.banking.controller;

import com.banking.dto.response.TransactionResponse;
import com.banking.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/admin/transactions")
@RequiredArgsConstructor
public class AdminTransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit")
    public TransactionResponse deposit(
            @RequestParam String accountNumber,
            @RequestParam BigDecimal amount,
            @RequestParam String description) {
        return transactionService.deposit(accountNumber, amount, description);
    }

    @PutMapping("/{referenceNumber}/reverse")
    public TransactionResponse reverse(@PathVariable String referenceNumber) {
        return transactionService.reverseTransaction(referenceNumber);
    }
}
