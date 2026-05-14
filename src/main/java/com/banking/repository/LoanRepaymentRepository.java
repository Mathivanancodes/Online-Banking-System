package com.banking.repository;

import com.banking.entity.LoanRepayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoanRepaymentRepository extends JpaRepository<LoanRepayment, UUID> {
    Page<LoanRepayment> findByLoanId(UUID loanId, Pageable pageable);
}
