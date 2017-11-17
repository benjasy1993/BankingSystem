package com.banking.prototype.repositories;

import com.banking.prototype.models.CompanyAccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyAccountRepository extends JpaRepository<CompanyAccountInfo, Long> {

    CompanyAccountInfo findFirstByBankAccountId(Long bankAccountId);
}
