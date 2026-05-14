package com.banking.service;

import com.banking.dto.request.TransferRequest;
import com.banking.dto.response.TransactionResponse;
import java.util.UUID;

public interface TransferService {
    TransactionResponse transfer(UUID userId, TransferRequest request);
}
