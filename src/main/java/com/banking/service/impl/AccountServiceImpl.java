package com.banking.service.impl;

import com.banking.dto.request.AccountCreateRequest;
import com.banking.dto.response.AccountResponse;
import com.banking.dto.response.PageResponse;
import com.banking.entity.Account;
import com.banking.entity.User;
import com.banking.enums.AccountStatus;
import com.banking.exception.ResourceNotFoundException;
import com.banking.mapper.EntityMapper;
import com.banking.repository.AccountRepository;
import com.banking.repository.UserRepository;
import com.banking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final EntityMapper mapper;

    @Override
    @Transactional
    public AccountResponse openAccount(UUID userId, AccountCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String accountNumber = "ACC" + System.currentTimeMillis() % 10000000;
        
        Account account = Account.builder()
                .accountNumber(accountNumber)
                .accountType(request.getAccountType())
                .status(AccountStatus.ACTIVE)
                .user(user)
                .build();

        account = accountRepository.save(account);
        return mapper.accountToResponse(account);
    }

    @Override
    public PageResponse<AccountResponse> getMyAccounts(UUID userId, Pageable pageable) {
        return null; // Impl
    }

    @Override
    public AccountResponse getAccountDetails(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .map(mapper::accountToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }

    @Override
    @Transactional
    public void closeAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        account.setStatus(AccountStatus.CLOSED);
        account.setClosedAt(LocalDateTime.now());
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void freezeAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        account.setStatus(AccountStatus.FROZEN);
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void unfreezeAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        account.setStatus(AccountStatus.ACTIVE);
        accountRepository.save(account);
    }
}
