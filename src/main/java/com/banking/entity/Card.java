package com.banking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String lastFourDigits;
    
    private String cardType; // DEBIT, CREDIT, PREPAID, VIRTUAL
    private String cardNetwork; // VISA, MASTERCARD, RUPAY
    
    private int expiryMonth;
    private int expiryYear;
    
    private String cvvHash;
    private String nameOnCard;
    
    private BigDecimal creditLimit;
    private BigDecimal availableLimit;
    
    private int billingCycleDay;
    
    private String status; // ACTIVE, BLOCKED, EXPIRED, CANCELLED, PENDING_APPROVAL
    
    @Builder.Default
    private boolean isContactless = false;
    
    @Builder.Default
    private boolean isInternationalEnabled = false;
    
    @Builder.Default
    private boolean isOnlineTxnEnabled = false;
    
    private String pinHash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}
