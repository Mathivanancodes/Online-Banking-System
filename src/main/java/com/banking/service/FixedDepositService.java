package com.banking.service;

import java.util.UUID;

public interface FixedDepositService {
    void createFixedDeposit(UUID userId, Object request);
}
