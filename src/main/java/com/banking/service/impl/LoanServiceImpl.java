package com.banking.service.impl;

import com.banking.dto.request.LoanApplyRequest;
import com.banking.dto.response.LoanResponse;
import com.banking.service.LoanService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class LoanServiceImpl implements LoanService {
    @Override
    public LoanResponse applyLoan(UUID userId, LoanApplyRequest request) { return null; }
}
