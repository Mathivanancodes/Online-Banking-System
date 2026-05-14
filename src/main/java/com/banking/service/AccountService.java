package com.banking.service;

import com.banking.dto.request.AccountCreateRequest;
import com.banking.dto.response.AccountResponse;
import com.banking.dto.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AccountService {
    AccountResponse openAccount(UUID userId, AccountCreateRequest request);
    PageResponse<AccountResponse> getMyAccounts(UUID userId, Pageable pageable);
    AccountResponse getAccountDetails(String accountNumber);
    void closeAccount(String accountNumber);
    void freezeAccount(String accountNumber);
    void unfreezeAccount(String accountNumber);
}
