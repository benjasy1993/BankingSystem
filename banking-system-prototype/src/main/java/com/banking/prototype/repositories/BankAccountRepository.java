package com.banking.prototype.repositories;

import com.banking.prototype.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findAllByInfo_UserId(Long userId);

    BankAccount findFirstByRoutingNumAndAccountNum(String routingNum, String accountNum);
}
