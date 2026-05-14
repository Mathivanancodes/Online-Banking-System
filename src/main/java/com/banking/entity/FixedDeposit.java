package com.banking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "fixed_deposits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FixedDeposit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private BigDecimal amount;
    private BigDecimal interestRate;
    private int tenureMonths;
    private BigDecimal maturityAmount;

    private LocalDate startDate;
    private LocalDate maturityDate;

    @Builder.Default
    private boolean autoRenew = false;

    private String status; // ACTIVE, MATURED, BROKEN_PREMATURELY

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}
