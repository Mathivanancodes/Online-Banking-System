package com.banking.dto.request;

import com.banking.enums.LoanType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanApplyRequest {
    @NotNull
    private LoanType loanType;
    
    @NotNull
    @DecimalMin("1000.0")
    private BigDecimal principalAmount;
    
    @Min(6)
    private int tenureMonths;
    
    @NotBlank
    private String purpose;
}
