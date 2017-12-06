package com.banking.prototype.repositories;


import com.banking.prototype.models.AccountInfo;
import com.banking.prototype.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, Long>{

    AccountInfo getAccountInfoByUserId(Long userId);

    AccountInfo findAccountInfoByBankAccountsContaining(BankAccount bankAccount);
}
