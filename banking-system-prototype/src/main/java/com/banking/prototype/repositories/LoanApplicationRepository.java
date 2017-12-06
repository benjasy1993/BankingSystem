package com.banking.prototype.repositories;

import com.banking.prototype.models.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {

    LoanApplication findByUserId(long userId);

}
