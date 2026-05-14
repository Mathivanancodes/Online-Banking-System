package com.banking.service.impl;

import com.banking.dto.response.TransactionResponse;
import com.banking.entity.Account;
import com.banking.entity.Transaction;
import com.banking.enums.AccountStatus;
import com.banking.enums.TransactionType;
import com.banking.exception.AccountFrozenException;
import com.banking.exception.InsufficientFundsException;
import com.banking.exception.ResourceNotFoundException;
import com.banking.mapper.EntityMapper;
import com.banking.repository.AccountRepository;
import com.banking.repository.TransactionRepository;
import com.banking.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final EntityMapper mapper;

    @Override
    @Transactional
    public TransactionResponse deposit(String accountNumber, BigDecimal amount, String description) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (account.getStatus() != AccountStatus.ACTIVE) {
            throw new AccountFrozenException("Account is not active");
        }

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .referenceNumber("TXN" + System.currentTimeMillis())
                .transactionType(TransactionType.DEPOSIT)
                .amount(amount)
                .balanceAfter(account.getBalance())
                .currency(account.getCurrency())
                .description(description)
                .status("COMPLETED")
                .destinationAccount(account)
                .completedAt(LocalDateTime.now())
                .build();

        transaction = transactionRepository.save(transaction);
        return mapper.transactionToResponse(transaction);
    }

    @Override
    @Transactional
    public TransactionResponse withdraw(UUID userId, String accountNumber, BigDecimal amount, String description) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (!account.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Account not found for user");
        }

        if (account.getStatus() != AccountStatus.ACTIVE) {
            throw new AccountFrozenException("Account is not active");
        }

        if (account.getBalance().subtract(amount).compareTo(account.getMinimumBalance()) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .referenceNumber("TXN" + System.currentTimeMillis())
                .transactionType(TransactionType.WITHDRAWAL)
                .amount(amount)
                .balanceAfter(account.getBalance())
                .currency(account.getCurrency())
                .description(description)
                .status("COMPLETED")
                .sourceAccount(account)
                .completedAt(LocalDateTime.now())
                .build();

        transaction = transactionRepository.save(transaction);
        return mapper.transactionToResponse(transaction);
    }

    @Override
    @Transactional
    public TransactionResponse reverseTransaction(String referenceNumber) {
        return null; // Implement
    }
}
