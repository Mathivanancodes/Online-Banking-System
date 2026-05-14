package com.banking.dto.request;

import com.banking.util.ValidAccountNumber;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private String transferType;
    
    @ValidAccountNumber
    private String senderAccountNumber;
    
    private String receiverAccountNumber; // Can be external
    
    private String receiverBankCode; // IFSC/SWIFT
    
    @NotNull
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;
    
    private String remarks;
}
