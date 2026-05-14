package com.banking.repository;

import com.banking.entity.FixedDeposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface FixedDepositRepository extends JpaRepository<FixedDeposit, UUID> {
    List<FixedDeposit> findByAccountUserId(UUID userId);
    List<FixedDeposit> findByStatusAndMaturityDate(String status, LocalDate maturityDate);
}
