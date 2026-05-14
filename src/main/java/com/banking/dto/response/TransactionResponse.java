package com.banking.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionResponse {
    private UUID id;
    private String referenceNumber;
    private String transactionType;
    private BigDecimal amount;
    private BigDecimal balanceAfter;
    private String currency;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
}
