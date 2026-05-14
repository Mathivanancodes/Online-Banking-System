package com.banking.repository;

import com.banking.entity.Loan;
import com.banking.enums.LoanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {
    List<Loan> findByUserId(UUID userId);
    Page<Loan> findByStatus(LoanStatus status, Pageable pageable);
    List<Loan> findByStatus(LoanStatus status);
}
