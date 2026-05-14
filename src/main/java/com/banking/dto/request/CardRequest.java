package com.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CardRequest {
    @NotBlank
    private String cardType;
    
    @NotBlank
    private String linkedAccountNumber;
}
