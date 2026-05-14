package com.banking.service;

import com.banking.dto.request.LoanApplyRequest;
import com.banking.dto.response.LoanResponse;
import java.util.UUID;

public interface LoanService {
    LoanResponse applyLoan(UUID userId, LoanApplyRequest request);
}
