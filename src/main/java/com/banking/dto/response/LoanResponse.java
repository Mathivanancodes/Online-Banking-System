package com.banking.dto.response;

import com.banking.enums.LoanStatus;
import com.banking.enums.LoanType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class LoanResponse {
    private UUID id;
    private LoanType loanType;
    private BigDecimal principalAmount;
    private BigDecimal outstandingAmount;
    private BigDecimal interestRate;
    private int tenureMonths;
    private BigDecimal emiAmount;
    private LocalDateTime nextDueDate;
    private LoanStatus status;
}
