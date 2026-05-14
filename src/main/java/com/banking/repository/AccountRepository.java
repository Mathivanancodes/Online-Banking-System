package com.banking.repository;

import com.banking.entity.Account;
import com.banking.enums.AccountStatus;
import com.banking.enums.AccountType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByAccountNumber(String accountNumber);
    boolean existsByAccountNumber(String accountNumber);
    List<Account> findByUserId(UUID userId);
    Page<Account> findByAccountTypeAndStatus(AccountType accountType, AccountStatus status, Pageable pageable);
    Page<Account> findByStatus(AccountStatus status, Pageable pageable);
    Page<Account> findByAccountType(AccountType type, Pageable pageable);
}
