package com.banking.service;

import com.banking.dto.request.TransferRequest;
import com.banking.dto.response.TransactionResponse;

import java.math.BigDecimal;
import java.util.UUID;

public interface TransactionService {
    TransactionResponse deposit(String accountNumber, BigDecimal amount, String description);
    TransactionResponse withdraw(UUID userId, String accountNumber, BigDecimal amount, String description);
    TransactionResponse reverseTransaction(String referenceNumber);
}
