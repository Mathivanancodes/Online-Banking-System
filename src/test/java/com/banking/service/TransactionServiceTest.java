package com.banking.service;

import com.banking.dto.response.TransactionResponse;
import com.banking.entity.Account;
import com.banking.entity.User;
import com.banking.enums.AccountStatus;
import com.banking.exception.InsufficientFundsException;
import com.banking.mapper.EntityMapper;
import com.banking.repository.AccountRepository;
import com.banking.repository.TransactionRepository;
import com.banking.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private EntityMapper mapper;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void withdraw_ShouldThrowException_WhenInsufficientFunds() {
        User user = new User();
        user.setId(UUID.randomUUID());
        
        Account account = new Account();
        account.setUser(user);
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(new BigDecimal("100.00"));
        account.setMinimumBalance(new BigDecimal("50.00"));

        when(accountRepository.findByAccountNumber(anyString())).thenReturn(Optional.of(account));

        assertThrows(InsufficientFundsException.class, () -> 
            transactionService.withdraw(user.getId(), "ACC123", new BigDecimal("60.00"), "Withdraw")
        );
    }
}
