package com.banking.repository;

import com.banking.entity.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, UUID> {
    Optional<Transfer> findByReferenceNumber(String referenceNumber);
    Page<Transfer> findBySenderAccountId(UUID senderAccountId, Pageable pageable);
    List<Transfer> findByStatusAndScheduledAtBefore(String status, LocalDateTime scheduledAt);
}
