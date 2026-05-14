package com.banking.service.impl;

import com.banking.dto.request.TransferRequest;
import com.banking.dto.response.TransactionResponse;
import com.banking.service.TransferService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class TransferServiceImpl implements TransferService {
    @Override
    public TransactionResponse transfer(UUID userId, TransferRequest request) { return null; }
}
