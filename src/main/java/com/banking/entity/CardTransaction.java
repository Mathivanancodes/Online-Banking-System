package com.banking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "card_transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardTransaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String merchantName;
    private String merchantCategory;
    
    private BigDecimal amount;
    private String currency;
    
    private String transactionType; // PURCHASE, REFUND, CASH_ADVANCE, EMI_CONVERSION
    
    private String status; // APPROVED, DECLINED, DISPUTED
    
    @Builder.Default
    private boolean isDisputed = false;
    
    private String disputeReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;
}
