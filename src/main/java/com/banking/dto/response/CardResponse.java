package com.banking.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CardResponse {
    private UUID id;
    private String lastFourDigits;
    private String cardType;
    private String cardNetwork;
    private int expiryMonth;
    private int expiryYear;
    private String nameOnCard;
    private BigDecimal availableLimit;
    private String status;
}
