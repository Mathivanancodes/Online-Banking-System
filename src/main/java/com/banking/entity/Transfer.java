package com.banking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transfers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String referenceNumber;

    private String transferType; // INTERNAL, EXTERNAL, INTERNATIONAL, UPI, RTGS, NEFT
    
    private UUID senderAccountId;
    private UUID receiverAccountId; // For internal transfers

    private BigDecimal amount;
    private String currency;
    private BigDecimal exchangeRate;
    private BigDecimal fees;
    private BigDecimal netAmount;

    private LocalDateTime scheduledAt;
    
    private String status; // PENDING, PROCESSING, COMPLETED, FAILED, CANCELLED
    private String remarks;
}
