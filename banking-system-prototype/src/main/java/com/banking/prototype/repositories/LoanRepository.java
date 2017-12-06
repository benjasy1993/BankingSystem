package com.banking.prototype.repositories;

import com.banking.prototype.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {


}
