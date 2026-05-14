package com.banking.service;

import com.banking.dto.request.AccountCreateRequest;
import com.banking.dto.response.AccountResponse;
import com.banking.entity.Account;
import com.banking.entity.User;
import com.banking.enums.AccountType;
import com.banking.exception.ResourceNotFoundException;
import com.banking.mapper.EntityMapper;
import com.banking.repository.AccountRepository;
import com.banking.repository.UserRepository;
import com.banking.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private EntityMapper mapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void openAccount_ShouldThrowException_WhenUserNotFound() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> accountService.openAccount(UUID.randomUUID(), new AccountCreateRequest()));
    }

    @Test
    void openAccount_ShouldSaveAccount_WhenValidRequest() {
        User user = new User();
        AccountCreateRequest request = new AccountCreateRequest();
        request.setAccountType(AccountType.SAVINGS);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(accountRepository.save(any(Account.class))).thenReturn(new Account());
        when(mapper.accountToResponse(any())).thenReturn(new AccountResponse());

        AccountResponse response = accountService.openAccount(UUID.randomUUID(), request);

        assertNotNull(response);
        verify(accountRepository).save(any(Account.class));
    }
}
