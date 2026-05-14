package com.banking.repository;

import com.banking.entity.CardTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardTransactionRepository extends JpaRepository<CardTransaction, UUID> {
    Page<CardTransaction> findByCardId(UUID cardId, Pageable pageable);
    Page<CardTransaction> findByIsDisputedTrue(Pageable pageable);
}
