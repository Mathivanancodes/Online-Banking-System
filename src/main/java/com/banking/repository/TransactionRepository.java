package com.banking.repository;

import com.banking.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Optional<Transaction> findByReferenceNumber(String referenceNumber);
    Page<Transaction> findBySourceAccountIdOrDestinationAccountId(UUID sourceAccountId, UUID destinationAccountId, Pageable pageable);
    Page<Transaction> findByInitiatedById(UUID userId, Pageable pageable);
}
